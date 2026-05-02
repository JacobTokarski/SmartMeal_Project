package org.example.smartmeal.ui.views.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoginSuccessful: Boolean = false,
)

class LoginViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())

    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()


    fun onEmailChange(newValue: String) {
        _uiState.value = uiState.value.copy(email = newValue, error = null)
    }

    fun onPasswordChange(newValue: String) {
        _uiState.value = uiState.value.copy(password = newValue, error = null,)
    }

    fun onLoginClick() {

        viewModelScope.launch {

            val currentState = _uiState.value

            _uiState.value = currentState.copy(isLoading = true, error = null)

            delay(1500)

            _uiState.value = currentState.copy(isLoading = false, isLoginSuccessful = true)
        }
    }
}



