package org.example.smartmeal.ui.views.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.smartmeal.data.repository.FavoriteRepository
import org.example.smartmeal.model.recipe.HomeDemoData
import org.example.smartmeal.model.recipe.Recipe

data class HomeUiState(
    val recommended: List<Recipe> = emptyList(),
    val breakfast: List<Recipe> = emptyList(),
    val lunch: List<Recipe> = emptyList(),
    val dinner: List<Recipe> = emptyList(),
    val favoriteIds: Set<String> = emptySet(),
)

class HomeViewModel : ViewModel() {

    val uiState: StateFlow<HomeUiState> = FavoriteRepository.favoriteIds

        .map { ids ->
            val all = HomeDemoData.allRecipes

            HomeUiState(
                recommended = all.take(5),
                breakfast = all.drop(5).take(5),
                lunch = all.drop(10).take(5),
                dinner = all.drop(15).take(5),
                favoriteIds = ids,
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState()
        )
}