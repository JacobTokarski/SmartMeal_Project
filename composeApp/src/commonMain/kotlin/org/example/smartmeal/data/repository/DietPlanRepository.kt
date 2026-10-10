package org.example.smartmeal.data.repository

import androidx.compose.runtime.mutableStateMapOf
import kotlinx.datetime.LocalDate
import org.example.smartmeal.data.repository.DietPlanRepository.assignedRecipes
import org.example.smartmeal.model.selection.RecipeReference

object DietPlanRepository {

    val assignedRecipes = mutableStateMapOf<String, RecipeReference>()

    private fun generateKey(date: LocalDate, mealName: String): String =
        "${date}_$mealName"

    fun assignRecipe(date: LocalDate, mealName: String, reference: RecipeReference) {
        assignedRecipes[generateKey(date, mealName)] = reference
    }

    fun getReference(date: LocalDate, mealName: String): RecipeReference? =
        assignedRecipes[generateKey(date, mealName)]


    fun removeRecipe(date: LocalDate, mealName: String) {
        assignedRecipes.remove(generateKey(date, mealName))
    }
}