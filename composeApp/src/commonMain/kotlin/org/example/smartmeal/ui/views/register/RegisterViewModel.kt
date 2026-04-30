package org.example.smartmeal.ui.views.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RegisterUIState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val confirmPassword: String = "",
    val confirmEmail: String = "",
    val isRegisterSuccessful: Boolean = false,
    val isLoading: Boolean = false,
)

class RegisterViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUIState())

    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

    fun onNicknameChange(newValue: String) {
        _uiState.value = uiState.value.copy(username = newValue, error = null)
    }

    fun onEmailChange(newValue: String) {
        _uiState.value = uiState.value.copy(email = newValue, error = null)
    }

    fun onEmailConfirm(newValue: String) {
        _uiState.value = uiState.value.copy(confirmEmail = newValue, error = null)
    }

    fun onPasswordChange(newValue: String) {
        _uiState.value = uiState.value.copy(password = newValue, error = null)
    }

    fun onPasswordConfirm(newValue: String) {
        _uiState.value = uiState.value.copy(confirmPassword = newValue, error = null)
    }

    fun onRegisterClick() {

        viewModelScope.launch {

            val currentState = _uiState.value

            _uiState.value = currentState.copy(isLoading = true, error = null)

            delay(1500)

            _uiState.value = currentState.copy(isLoading = false, isRegisterSuccessful = true)
        }
    }
}