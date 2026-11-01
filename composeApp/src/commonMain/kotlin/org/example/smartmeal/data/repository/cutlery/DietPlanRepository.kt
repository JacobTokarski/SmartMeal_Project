package org.example.smartmeal.data.repository.cutlery

import androidx.compose.runtime.mutableStateMapOf
import kotlinx.datetime.LocalDate
import org.example.smartmeal.data.remote.cloud.CloudScope
import org.example.smartmeal.data.remote.cloud.MealPlanDoc
import org.example.smartmeal.data.remote.cloud.UserCloud
import org.example.smartmeal.model.selection.RecipeReference
import org.example.smartmeal.model.selection.RecipeSource

object DietPlanRepository {

    val assignedRecipes = mutableStateMapOf<String, RecipeReference>()

    private fun generateKey(date: LocalDate, mealName: String): String =
        "${date}_$mealName"

    fun assignRecipe(date: LocalDate, mealName: String, reference: RecipeReference) {
        val key = generateKey(date, mealName)
        assignedRecipes[key] = reference
        val ref = UserCloud.document("mealPlan", key)
        CloudScope.write {
            ref?.set(
                MealPlanDoc.serializer(),
                MealPlanDoc(key, date.toString(), mealName, reference.id, reference.source.name)
            ) { encodeDefaults = true }
        }
    }

    fun getReference(date: LocalDate, mealName: String): RecipeReference? =
        assignedRecipes[generateKey(date, mealName)]

    fun removeRecipe(date: LocalDate, mealName: String) {
        val key = generateKey(date, mealName)
        assignedRecipes.remove(key)
        val ref = UserCloud.document("mealPlan", key)
        CloudScope.write { ref?.delete() }
    }

    fun removeReferencesTo(reference: RecipeReference) {
        val keys = assignedRecipes.filterValues { it == reference }.keys.toList()
        keys.forEach { key ->
            assignedRecipes.remove(key)
            val ref = UserCloud.document("mealPlan", key)
            CloudScope.write { ref?.delete() }
        }
    }

    suspend fun loadFromCloud() {
        val docs = UserCloud.collection("mealPlan")?.get()?.documents ?: return
        assignedRecipes.clear()
        docs.forEach { snap ->
            val d = snap.data(MealPlanDoc.serializer())
            val source = runCatching { RecipeSource.valueOf(d.source) }.getOrNull() ?: return@forEach
            assignedRecipes[d.key] = RecipeReference(d.recipeId, source)
        }
    }

    fun clear() = assignedRecipes.clear()
}