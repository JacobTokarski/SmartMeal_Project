package org.example.smartmeal.data.repository.favorite

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.smartmeal.data.remote.cloud.CloudScope
import org.example.smartmeal.data.remote.cloud.FavoritesDoc
import org.example.smartmeal.data.remote.cloud.UserCloud

class FavoriteRepository {
    private val _favoriteIds = MutableStateFlow<Set<String>>(emptySet())
    val favoriteIds: StateFlow<Set<String>> = _favoriteIds.asStateFlow()

    fun toggle(recipeId: String) {
        _favoriteIds.update { current ->
            if (recipeId in current) current - recipeId else current + recipeId
        }
        val snapshot = favoriteIds.value.toList()
        val ref = UserCloud.document("meta", "favorites")
        CloudScope.write {
            ref?.set(FavoritesDoc.serializer(), FavoritesDoc(snapshot)) { encodeDefaults = true }
        }
    }

    suspend fun loadFromCloud() {
        val doc = UserCloud.document("meta", "favorites")?.get() ?: return
        if (doc.exists) _favoriteIds.value = doc.data(FavoritesDoc.serializer()).ids.toSet()
    }

    fun clear() { _favoriteIds.value = emptySet() }
}