package org.example.smartmeal.ui.views.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.ui.components.CustomButtonField
import org.example.smartmeal.ui.components.CustomEmailField
import org.example.smartmeal.ui.components.CustomPasswordField
import org.example.smartmeal.ui.theme.Colors
import org.example.smartmeal.ui.views.login.LoginScreen
import org.example.smartmeal.ui.views.register.parts.RegisterFooter
import org.example.smartmeal.ui.views.register.parts.RegisterForm
import org.example.smartmeal.ui.views.register.parts.RegisterHeader
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_arrow
import smartmeal_project.composeapp.generated.resources.ic_lock
import smartmeal_project.composeapp.generated.resources.ic_person
import smartmeal_project.composeapp.generated.resources.pic_reg_leaf

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
                .padding(horizontal = 20.dp)
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