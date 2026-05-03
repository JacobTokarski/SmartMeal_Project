package org.example.smartmeal.ui.views.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.smartmeal.ui.utils.AuthError


data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoginSuccessful: Boolean = false,
    val emailError: AuthError = AuthError.None,
    val passwordError: AuthError = AuthError.None,
)

class LoginViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())

    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()


    fun onEmailChange(newValue: String) {
        _uiState.value = uiState.value.copy(email = newValue, emailError = AuthError.None)
    }

    fun onPasswordChange(newValue: String) {
        _uiState.value = uiState.value.copy(password = newValue, passwordError = AuthError.None,)
    }

    fun onLoginClick() {

        val currentState = _uiState.value

        val mailError = if (currentState.email.isBlank()) AuthError.EmptyField else AuthError.None
        val passwordError = if (currentState.password.isBlank()) AuthError.EmptyField else AuthError.None

        _uiState.update { it.copy(
            emailError = mailError,
            passwordError = passwordError
        ) }

        if (mailError == AuthError.None && passwordError == AuthError.None) {
            _uiState.update { it.copy(isLoginSuccessful = true) } // W przyszłości tutaj będzie się znajdować logika Firebase-a
        }
    }
}



