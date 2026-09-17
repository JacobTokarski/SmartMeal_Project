package org.example.smartmeal.ui.views.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.smartmeal.ui.views.home.HomeDemoData
import org.example.smartmeal.ui.views.home.HomeRecipe

data class SearchRecipeUIState(
    val recipes: List<HomeRecipe> = emptyList(),
    val searchQuery: String = "",
)
class SearchViewModel: ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    val uiState: StateFlow<SearchRecipeUIState> = _searchQuery
        .map { query ->
            val filtered = if (query.isBlank()) {
                HomeDemoData.allRecipes
            } else {
                HomeDemoData.allRecipes.filter {
                    it.title.contains(query, ignoreCase = true)
                }
            }

            SearchRecipeUIState(searchQuery = query, recipes = filtered)
        }

        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SearchRecipeUIState(recipes = HomeDemoData.allRecipes)
        )

    fun onSearchQuery(value: String) {
        _searchQuery.value = value
    }
}