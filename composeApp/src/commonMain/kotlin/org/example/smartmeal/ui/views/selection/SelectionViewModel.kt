package org.example.smartmeal.ui.views.selection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.datetime.LocalDate
import org.example.smartmeal.data.repository.CustomRecipe
import org.example.smartmeal.data.repository.DietPlanRepository
import org.example.smartmeal.data.repository.RecipeRepository
class SelectionViewModel(
    private val mealName: String,
    private val selectedDate: LocalDate
) : ViewModel() {

    init {
        println("DEBUG_TAG: Inicjalizacja SelectionViewModel")
        println("DEBUG_TAG: Zawartość RecipeRepository przy starcie: ${RecipeRepository.userRecipes.map { it.title }}")
        println("DEBUG_TAG: Łączna liczba przepisów w repozytorium: ${RecipeRepository.userRecipes.size}")
    }

    var searchQuery by mutableStateOf("")
        private set

    var selectedRecipeId by mutableStateOf<String?>(null)
        private set

    val ownRecipes: List<CustomRecipe>
        get() = RecipeRepository.userRecipes.filter {
            it.title.contains(searchQuery, ignoreCase = true)
        }

    val favoriteRecipes: List<CustomRecipe>
        get() = RecipeRepository.userRecipes.filter {
            it.isFavorite && it.title.contains(searchQuery, ignoreCase = true)
        }

    fun onSearchQueryChanged(newQuery: String) {
        searchQuery = newQuery
    }

    fun toggleRecipeSelection(recipeId: String) {
        selectedRecipeId = if (selectedRecipeId == recipeId) null else recipeId
    }

    fun confirmSelection() {
        selectedRecipeId?.let { recipeId ->
            DietPlanRepository.assignRecipe(selectedDate, mealName, recipeId)
        }
    }
}