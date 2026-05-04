package org.example.smartmeal.ui.views.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.ui.views.login.parts.LoginFooter
import org.example.smartmeal.ui.views.login.parts.LoginForm
import org.example.smartmeal.ui.views.login.parts.LoginHeader
import org.example.smartmeal.ui.views.main.MainScreen
import org.example.smartmeal.ui.views.register.RegisterScreen
import org.koin.compose.viewmodel.koinViewModel


object LoginScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<LoginViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        val state by viewModel.uiState.collectAsState()

        LaunchedEffect(state.isLoginSuccessful) {
            if (state.isLoginSuccessful) {
                navigator.replaceAll(MainScreen)
            }
        }

        LoginContent(
            viewModel = viewModel,
            onRegisterClick = {
                navigator.push(RegisterScreen)
            }
        )
    }
}

@Composable
fun LoginContent(
    viewModel: LoginViewModel,
    onRegisterClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            LoginHeader()

            LoginForm(
                email = state.email,
                password = state.password,
                emailError = state.emailError,
                passwordError = state.passwordError,
                onEmailChange = { viewModel.onEmailChange(it)},
                onPasswordChange = { viewModel.onPasswordChange(it)},
                onLoginClick = {viewModel.onLoginClick()}
            )

            LoginFooter(
                onRegisterClick = onRegisterClick
            )
        }
    }
}
