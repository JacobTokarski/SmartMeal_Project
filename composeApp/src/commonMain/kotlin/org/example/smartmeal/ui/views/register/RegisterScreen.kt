package org.example.smartmeal.ui.views.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.ui.views.login.LoginScreen
import org.example.smartmeal.ui.views.register.parts.RegisterFooter
import org.example.smartmeal.ui.views.register.parts.RegisterForm
import org.example.smartmeal.ui.views.register.parts.RegisterHeader
import org.koin.compose.viewmodel.koinViewModel

object RegisterScreen : Screen {
    @Composable
    override fun Content() {

        val viewModel = koinViewModel<RegisterViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        RegisterContent(
            viewModel = viewModel,
            onLoginClick = {
                navigator.push(LoginScreen)
            },
            onBackClick = {
                navigator.push(LoginScreen)
            }
        )
    }
}

@Composable
fun RegisterContent(
    viewModel: RegisterViewModel,
    onLoginClick: () -> Unit,
    onBackClick: () -> Unit
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
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .systemBarsPadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            RegisterHeader(
                onBackClick = onBackClick
            )

            RegisterForm(
                state = state,
                username = state.username,
                onNicknameChange = { viewModel.onNicknameChange(it) },
                email = state.email,
                onEmailChange = { viewModel.onEmailChange(it) },
                confirmEmail = state.confirmEmail,
                onEmailConfirm = { viewModel.onEmailConfirm(it) },
                password = state.password,
                onPasswordChange = { viewModel.onPasswordChange(it) },
                confirmPassword = state.confirmPassword,
                onPasswordConfirm = { viewModel.onPasswordConfirm(it) },
                onRegisterClick = { viewModel.onRegisterClick() },
            )

            RegisterFooter(
                onLoginClick = onLoginClick
            )
        }
    }
}