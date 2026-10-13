package org.example.smartmeal.data.repository.home

import androidx.compose.runtime.mutableStateListOf

data class CustomRecipe(
    val id: String,
    val title: String,
    val category: String,
    val type: String,
    val hasImage: Boolean,
    val calories: String,
    val time: String,
    val isFavorite: Boolean
)

object RecipeRepository {
    val userRecipes = mutableStateListOf<CustomRecipe>()

    fun addRecipe(recipe: CustomRecipe) {
        userRecipes.add(recipe)
    }

    fun updateRecipe(updated: CustomRecipe) {
        val index = userRecipes.indexOfFirst { it.id == updated.id }
        if (index >= 0) userRecipes[index] = updated
    }

    fun removeRecipe(id: String) {
        userRecipes.removeAll { it.id == id }
    }
}