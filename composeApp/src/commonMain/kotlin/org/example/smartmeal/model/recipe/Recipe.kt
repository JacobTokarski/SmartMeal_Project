package org.example.smartmeal.model.recipe

data class Recipe(
    val id: String = "",
    val title: String,
    val calories: String,
    val time: String,
    val type: String,
    val category: String,
    val hasImage: Boolean = false
)

object HomeDemoData {
    val allRecipes = List(20) { index ->
        Recipe(
            id = "demo_$index",
            title = "Kotlet schabowy z ziemniakami",
            category = "Obiad",
            type = "Polska",
            hasImage = true,
            calories = "2000kcal",
            time = "20 minut",
        )
    }
}