package org.example.smartmeal.data.remote.cloud

import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.example.smartmeal.data.repository.authentication.AuthenticationRepository
import org.example.smartmeal.data.repository.cutlery.DietPlanRepository
import org.example.smartmeal.data.repository.favorite.FavoriteRepository
import org.example.smartmeal.data.repository.home.RecipeRepository

class SessionDataSync(
    private val authRepository: AuthenticationRepository,
    private val favoriteRepository: FavoriteRepository,
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun start() {
        scope.launch {
            authRepository.currentUser
                .map { it?.uid }
                .distinctUntilChanged()
                .collectLatest { uid ->
                    if (uid == null) clearAll() else loadAll()
                }
        }
    }

    private fun clearAll() {
        RecipeRepository.clear()
        favoriteRepository.clear()
        DietPlanRepository.clear()
    }

    private suspend fun loadAll() {
        try {
            RecipeRepository.loadFromCloud()
            favoriteRepository.loadFromCloud()
            DietPlanRepository.loadFromCloud()
        } catch (e: Exception) {
            Napier.e("Nie udało się wczytać danych użytkownika", e)
        }
    }
}