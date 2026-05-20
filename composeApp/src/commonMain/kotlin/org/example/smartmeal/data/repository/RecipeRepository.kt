package org.example.smartmeal.data.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

data class CustomRecipe(
    val id: String,
    val title: String,
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
}