package org.example.smartmeal.ui.views.own

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.smartmeal.ui.utils.Recipe
import org.koin.viewmodel.emptyState

data class OwnRecipeUIState(
    val recipes: List<Recipe> = emptyList(),
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

    fun onDeleteRecipe() {} // tutaj będzie się znajdować logika usuwania naszego przepisu

    fun onAddRecipe(newRecipe: Recipe) {

        _uiState.update {
            it.copy(
                recipes = it.recipes + newRecipe,
                isFormVisible = false
            )
        }
    }
}