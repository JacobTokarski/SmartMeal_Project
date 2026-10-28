package org.example.smartmeal.data.remote.cloud

import kotlinx.serialization.Serializable
import org.example.smartmeal.data.repository.home.CustomRecipe

@Serializable
data class RecipeDoc(
    val id: String = "",
    val title: String = "",
    val category: String = "",
    val type: String = "",
    val imageUrl: String? = null,
    val calories: String = "",
    val time: String = "",
    val isFavorite: Boolean = false
)

fun CustomRecipe.toDoc() = RecipeDoc(
    id = id,
    title = title,
    category = category,
    type = type,
    imageUrl = imageUrl,
    calories = calories,
    time = time,
    isFavorite = isFavorite
)

fun RecipeDoc.toDomain() = CustomRecipe(
    id = id,
    title = title,
    category = category,
    type = type,
    imageUrl = imageUrl,
    calories = calories,
    time = time,
    isFavorite = isFavorite
)

@Serializable
data class FavoritesDoc(val ids: List<String> = emptyList())

@Serializable
data class MealPlanDoc(
    val key: String = "",
    val date: String = "",
    val meal: String = "",
    val recipeId: String = "",
    val source: String = "",
)