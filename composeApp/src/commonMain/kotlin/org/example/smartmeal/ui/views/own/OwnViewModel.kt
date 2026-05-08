package org.example.smartmeal.ui.views.own

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class OwnRecipeUIState(
    val recipes: List<String> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
)

class OwnViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(OwnRecipeUIState())

    val uiState = _uiState.asStateFlow()

    fun onSearchQuery(newQuery: String) {
        _uiState.value = _uiState.value.copy(searchQuery = newQuery)
    }

    fun onAddRecipe() {
        _uiState.value = _uiState.value.copy(
            recipes = listOf("Nowy Przepis")
        )
    }
}