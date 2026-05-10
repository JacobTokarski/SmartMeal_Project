package org.example.smartmeal.ui.views.own

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.smartmeal.ui.utils.Recipe

data class OwnRecipeUIState(
    val recipes: List<Recipe> = listOf(
        Recipe(title = "Grillowany łosoś", calories = "450kcal", time = "25min", hasImage = true),
        Recipe(title = "Burger wieprzowy", calories = "1500kcal", time = "35min", hasImage = false)
    ),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isFormVisible: Boolean = false
)

class OwnViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(OwnRecipeUIState())

    fun onToggleForm(visible: Boolean) {
        _uiState.update { it.copy(isFormVisible = visible) }
    }
    val uiState = _uiState.asStateFlow()

    fun onSearchQuery(newQuery: String) {
        _uiState.value = _uiState.value.copy(searchQuery = newQuery)
    }

    fun onEditRecipe() {} //

    fun onDeleteRecipe() {} //

    fun onAddRecipe(newRecipe: Recipe) {

//        _uiState.value = _uiState.value.copy(
//            recipes = listOf("Nowy Przepis")
//        )

        _uiState.update {
            it.copy(
                recipes = it.recipes + newRecipe,
                isFormVisible = false
            )
        }
    }
}