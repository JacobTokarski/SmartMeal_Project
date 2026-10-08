package org.example.smartmeal.data.repository.favorite

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FavoriteRepository {
    private val _favoriteIds = MutableStateFlow<Set<String>>(emptySet())
    val favoriteIds: StateFlow<Set<String>> = _favoriteIds.asStateFlow()

    fun toggle(recipeId: String) {
        _favoriteIds.update { current ->
            if (recipeId in current) current - recipeId else current + recipeId
        }
    }
}