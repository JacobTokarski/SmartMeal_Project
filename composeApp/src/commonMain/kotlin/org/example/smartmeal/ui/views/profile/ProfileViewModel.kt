package org.example.smartmeal.ui.views.profile

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.example.smartmeal.data.repository.AuthenticationRepository
import org.example.smartmeal.data.repository.FavoriteRepository
import org.example.smartmeal.data.repository.RecipeRepository


data class ProfileUIState(
    val displayName: String = "Użytkowniku",
    val avatarName: String = "U",
    val favoriteCount: Int = 0,
    val ownRecipesCount: Int = 0,
)

class ProfileViewModel(
    private val authRepository: AuthenticationRepository
) : ViewModel() {

    val uiState: StateFlow<ProfileUIState> = combine(
        authRepository.currentUser,
        FavoriteRepository.favoriteIds,
        snapshotFlow { RecipeRepository.userRecipes.size }
    ) { user, favoriteIds, ownCount ->

        val name = user?.displayName?.takeIf { it.isNotBlank() } ?: "Użytkowniku"

        ProfileUIState(
            displayName = name,
            avatarName = name.first().uppercase(),
            favoriteCount = favoriteIds.size,
            ownRecipesCount = ownCount
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProfileUIState()
    )

    private val _logoutEvent = MutableStateFlow(false)
    val logoutEvent: StateFlow<Boolean> = _logoutEvent

    fun onLogoutClick() {
        viewModelScope.launch {
            authRepository.logout()
            _logoutEvent.value = true
        }
    }
}