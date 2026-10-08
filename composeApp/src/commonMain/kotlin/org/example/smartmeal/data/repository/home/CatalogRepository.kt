package org.example.smartmeal.data.repository.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.smartmeal.data.remote.MealDbApiService
import org.example.smartmeal.model.recipe.Recipe
import org.example.smartmeal.model.recipe.toRecipe

sealed class CatalogState {
    object Loading: CatalogState()
    data class Loaded(val recipes: List<Recipe>): CatalogState()
    data class Error(val message: String): CatalogState()
}

class CatalogRepository(
    private val apiService: MealDbApiService
) {
    private val _state = MutableStateFlow<CatalogState>(CatalogState.Loading)
    val state: StateFlow<CatalogState> = _state.asStateFlow()

    private val lettersToFetch = listOf('b', 'c', 's',)

    suspend fun loadCatalog() {

        if (_state.value is CatalogState.Loaded) return

        _state.value = CatalogState.Loading

        try {
            val allRecipes = lettersToFetch
                .flatMap { letter -> apiService.searchByFirstLetter(letter).meals.orEmpty() }
                .distinctBy { it.id }
                .map { it.toRecipe()}

            _state.value = CatalogState.Loaded(allRecipes)
        } catch (e: Exception) {
            _state.value = CatalogState.Error(e.message ?: "Brak połączenia z internetem - nie można pobrać przepisów")
        }
    }
}