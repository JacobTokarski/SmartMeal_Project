package org.example.smartmeal.ui.views.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.example.smartmeal.data.repository.favorite.FavoriteRepository
import org.example.smartmeal.data.repository.home.CatalogRepository
import org.example.smartmeal.data.repository.home.CatalogState
import org.example.smartmeal.model.recipe.Recipe


data class FavoriteUIState(
    val recipes: List<Recipe> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
)
class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository,
    private val catalogRepository: CatalogRepository
): ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    fun onSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onToggleFavorite(recipeId: String) {
        favoriteRepository.toggle(recipeId)
    }

    val uiState: StateFlow<FavoriteUIState> = combine(
        favoriteRepository.favoriteIds,
        catalogRepository.state,
        _searchQuery
    ) { favoriteIds, catalogState, query ->

        val allRecipes = (catalogState as? CatalogState.Loaded)?.recipes.orEmpty()

        val favorites = allRecipes
            .filter { it.id in favoriteIds }
            .map { it.copy(isFavorite = true) }

        val filtered = if (query.isBlank()) {
            favorites
        } else {
            favorites.filter { it.title.contains(query, ignoreCase = true) }
        }

        FavoriteUIState(
            recipes = filtered,
            searchQuery = query,
            isLoading = false)

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FavoriteUIState(isLoading = true)
    )
}