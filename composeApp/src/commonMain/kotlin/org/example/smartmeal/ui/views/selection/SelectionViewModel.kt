package org.example.smartmeal.ui.views.selection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate
import org.example.smartmeal.data.repository.home.CustomRecipe
import org.example.smartmeal.data.repository.DietPlanRepository
import org.example.smartmeal.data.repository.favorite.FavoriteRepository
import org.example.smartmeal.data.repository.home.CatalogRepository
import org.example.smartmeal.data.repository.home.CatalogState
import org.example.smartmeal.data.repository.home.RecipeRepository
import org.example.smartmeal.model.recipe.Recipe
import org.example.smartmeal.model.selection.RecipeReference
import org.example.smartmeal.model.selection.SelectableRecipe
import org.example.smartmeal.model.selection.toSelectable


data class SelectionUIState(
    val ownRecipes: List<SelectableRecipe> = emptyList(),
    val favoriteRecipes: List<SelectableRecipe> = emptyList(),
    val selected: RecipeReference? = null,
)

class SelectionViewModel(
    private val mealName: String,
    private val selectedDate: LocalDate,
    catalogRepository: CatalogRepository,
    favoriteRepository: FavoriteRepository
) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    private val _selected = MutableStateFlow<RecipeReference?>(null)

    val uiState: StateFlow<SelectionUIState> = combine(
        catalogRepository.state,
        favoriteRepository.favoriteIds,
        snapshotFlow { RecipeRepository.userRecipes.toList() },
        snapshotFlow { searchQuery },
        _selected,
    ) { catalogState, favoriteIds, ownRecipes, query, selected ->

        val catalog = (catalogState as? CatalogState.Loaded)?.recipes.orEmpty()

        SelectionUIState(
            ownRecipes = ownRecipes
                .filter { it.title.contains(query, ignoreCase = true) }
                .map { it.toSelectable() },
            favoriteRecipes = catalog
                .filter { it.id in favoriteIds && it.title.contains(query, ignoreCase = true) }
                .map { it.toSelectable() },
            selected = selected
        )
    }. stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SelectionUIState())
    
        fun onSearchQueryChanged(newQuery: String) {
            searchQuery = newQuery
        }

        fun toggleRecipeSelection(reference: RecipeReference) {
            _selected.value = if (_selected.value == reference ) null else reference
        }

        fun confirmSelection() {
            _selected.value?.let { reference ->
                DietPlanRepository.assignRecipe(selectedDate, mealName, reference)
            }
        }
    }