package org.example.smartmeal.model.recipe

data class Recipe(
    val id: String = "",
    val title: String,
    val calories: String,
    val time: String,
    val type: String,
    val category: String,
    val hasImage: Boolean = false,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false
)