package org.example.smartmeal.model.utils.recipe

// Just for now
data class Recipe(
    val id: String = "",
    val title: String,
    val calories: String,
    val time: String,
    val type: String,
    val category: String,
    val hasImage: Boolean = false
)