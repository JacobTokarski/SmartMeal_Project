package org.example.smartmeal.ui.views.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.example.smartmeal.data.repository.AuthenticationRepository
import org.example.smartmeal.data.repository.favorite.FavoriteRepository
import org.example.smartmeal.data.repository.home.CatalogRepository
import org.example.smartmeal.data.repository.home.CatalogState
import org.example.smartmeal.model.recipe.Recipe

data class HomeUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val recommended: List<Recipe> = emptyList(),
    val breakfast: List<Recipe> = emptyList(),
    val lunch: List<Recipe> = emptyList(),
    val dinner: List<Recipe> = emptyList(),
    val favoriteIds: Set<String> = emptySet(),
    val displayName: String = "Użytkowniku"
)

class HomeViewModel(
    private val catalogRepository: CatalogRepository,
    private val authRepository: AuthenticationRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    fun onToggleFavorite(recipeId: String) {
        favoriteRepository.toggle(recipeId)
    }

    init {
        viewModelScope.launch { catalogRepository.loadCatalog() }
    }

    val uiState: StateFlow<HomeUiState> = combine(
        catalogRepository.state,
        authRepository.currentUser,
        favoriteRepository.favoriteIds
    ) { catalogState, user, favoriteIds ->

        val name = user?.displayName?.takeIf { it.isNotBlank() } ?: "Użytkowniku"

        when (catalogState) {

            is CatalogState.Loading -> HomeUiState(isLoading = true, displayName = name)
            is CatalogState.Error -> HomeUiState(isLoading = false, errorMessage = catalogState.message, displayName = name
            )

            is CatalogState.Loaded -> {

                val allWithFavorites = catalogState.recipes.map { recipe ->
                    recipe.copy(isFavorite = recipe.id in favoriteIds)
                }

                val recommended = allWithFavorites.take(5)
                val remaining = allWithFavorites.drop(5)

                HomeUiState(
                    isLoading = false,
                    recommended = recommended,
                    breakfast = remaining.filter { it.category == "Śniadanie" },
                    lunch = remaining.filter { it.category == "Obiad" },
                    dinner = remaining.filter { it.category == "Kolacja" },
                    displayName = name,
                    favoriteIds = favoriteIds,
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )
}