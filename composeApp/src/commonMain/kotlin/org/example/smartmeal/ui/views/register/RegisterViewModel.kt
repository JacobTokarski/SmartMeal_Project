package org.example.smartmeal.ui.views.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.smartmeal.ui.utils.AuthError

data class RegisterUIState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val confirmPassword: String = "",
    val confirmEmail: String = "",
    val isRegisterSuccessful: Boolean = false,
    val isLoading: Boolean = false,
    val usernameError: AuthError = AuthError.None,
    val emailError: AuthError = AuthError.None,
    val confirmEmailError: AuthError = AuthError.None,
    val passwordError: AuthError = AuthError.None,
    val confirmPasswordError: AuthError = AuthError.None
)

class RegisterViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUIState())

    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

    fun onNicknameChange(newValue: String) {
        _uiState.value = uiState.value.copy(username = newValue, usernameError = AuthError.None)
    }

    fun onEmailChange(newValue: String) {
        _uiState.value = uiState.value.copy(email = newValue, emailError = AuthError.None)
    }

    fun onEmailConfirm(newValue: String) {
        _uiState.value = uiState.value.copy(confirmEmail = newValue, confirmEmailError = AuthError.None)
    }

    fun onPasswordChange(newValue: String) {
        _uiState.value = uiState.value.copy(password = newValue, passwordError = AuthError.None)
    }

    fun onPasswordConfirm(newValue: String) {
        _uiState.value = uiState.value.copy(confirmPassword = newValue, confirmPasswordError = AuthError.None)
    }

    fun onRegisterClick() {

        val currentState = _uiState.value

        val usernameError = if (currentState.username.isBlank()) AuthError.EmptyField else AuthError.None

        val mailError = when {
            currentState.email.isBlank() -> AuthError.EmptyField
            !currentState.email.contains("@") -> AuthError.InvalidEmailFormat
            else -> AuthError.None
        }

        val confirmMailError = if (currentState.email != currentState.confirmEmail) {
            AuthError.EmailsDoNotMatch
        } else
            AuthError.None

        val passwordError = when {
            currentState.password.isBlank() -> AuthError.EmptyField
            currentState.password.length < 6 -> AuthError.PasswordTooShort
            !currentState.password.any { it.isUpperCase()} -> AuthError.MissingUppercase
            !currentState.password.any { it.isLetterOrDigit()} -> AuthError.MissingSpecialChar
            else -> AuthError.None
        }

        val confirmPasswordError = if (currentState.password != currentState.confirmPassword) {
            AuthError.PasswordsDoNotMatch
        } else
            AuthError.None

        _uiState.update { it.copy(
            usernameError = usernameError,
            emailError = mailError,
            confirmEmailError = confirmMailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError
        ) }

        val isDataValid = listOf(usernameError, mailError, confirmMailError, passwordError, confirmPasswordError)
            .all { it == AuthError.None }

        if (isDataValid) {
            // W przyszłości logika Firebase-a
        }
    }
}