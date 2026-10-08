package org.example.smartmeal.ui.views.search

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

data class SearchRecipeUIState(
    val recipes: List<Recipe> = emptyList(),
    val searchQuery: String = "",
)
class SearchViewModel(
    private val catalogRepository: CatalogRepository,
    private val favoriteRepository: FavoriteRepository
): ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    fun onSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onToggleFavorite(recipeId: String) {
        favoriteRepository.toggle(recipeId)
    }

    val uiState: StateFlow<SearchRecipeUIState> = combine(
        catalogRepository.state,
        favoriteRepository.favoriteIds,
        _searchQuery
    ) { catalogState, favoriteIds ,  query ->

        val all = (catalogState as? CatalogState.Loaded)?.recipes.orEmpty()
        val filtered = if (query.isBlank()) all else all.filter { it.title.contains(query, ignoreCase = true) }

        val mappedWithFavorites = filtered.map { recipe ->
            recipe.copy(isFavorite = recipe.id in favoriteIds)
        }

        SearchRecipeUIState(
            recipes = mappedWithFavorites,
            searchQuery = query
        )

    } .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SearchRecipeUIState()
    )
}