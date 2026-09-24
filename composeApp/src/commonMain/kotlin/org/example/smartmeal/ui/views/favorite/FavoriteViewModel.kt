package org.example.smartmeal.ui.views.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.example.smartmeal.data.repository.FavoriteRepository
import org.example.smartmeal.model.recipe.HomeDemoData
import org.example.smartmeal.model.recipe.Recipe


data class FavoriteUIState(
    val recipes: List<Recipe> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
)
class FavoriteViewModel: ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    val uiState: StateFlow<FavoriteUIState> = combine(
        FavoriteRepository.favoriteIds,
        _searchQuery
    ) { favoriteIds, query ->
        val favorites = HomeDemoData.allRecipes.filter { it.id in favoriteIds }
        val filtered = if (query.isBlank()) {
            favorites
        } else {
            favorites.filter { it.title.contains(query, ignoreCase = true) }
        }
        FavoriteUIState(recipes = filtered, searchQuery = query, isLoading = false)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FavoriteUIState(isLoading = true)
    )

    fun onSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }
}