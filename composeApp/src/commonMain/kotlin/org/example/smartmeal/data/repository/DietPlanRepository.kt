package org.example.smartmeal.data.repository

import androidx.compose.runtime.mutableStateMapOf
import kotlinx.datetime.LocalDate

object DietPlanRepository {

    val assignedRecipes = mutableStateMapOf<String, String>()

    private  fun generateKey(date: LocalDate, mealName: String): String {
        return "${date}_$mealName"
    }

    fun assignRecipe(date: LocalDate, mealName: String, recipeId: String) {
        val key = generateKey(date, mealName)
        assignedRecipes[key] = recipeId
    }

    fun getRecipeId(date: LocalDate, mealName: String) : String? {
        val key = generateKey(date, mealName)
        return assignedRecipes[key]
    }

    fun removeRecipe(date: LocalDate, mealName: String) {
        val key = generateKey(date, mealName)
        assignedRecipes.remove(key)
    }
}