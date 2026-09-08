package org.example.smartmeal.ui.views.home

import androidx.lifecycle.ViewModel

// Just for test
data class HomeRecipe(
    val id: String,
    val title: String,
    val category: String,
    val cuisineType: String,
    val hasImage: Boolean,
    val calories: String,
    val time: String,
)

// Just for test

object HomeDemoData {
    val allRecipes = List(20) { index ->
        HomeRecipe(
            id = "demo_$index",
            title = "Kotlet schabowy z ziemniakami",
            category = "Obiad",
            cuisineType = "Polska",
            hasImage = true,
            calories = "2000kcal",
            time = "20 minut",
        )
    }
}

class HomeViewModel: ViewModel() {
}