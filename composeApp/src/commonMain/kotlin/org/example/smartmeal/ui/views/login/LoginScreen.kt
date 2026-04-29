package org.example.smartmeal.ui.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.ui.components.CustomButtonField
import org.example.smartmeal.ui.components.CustomPasswordField
import org.example.smartmeal.ui.components.CustomTextField
import org.example.smartmeal.ui.theme.Colors
import org.example.smartmeal.ui.views.register.RegisterScreen
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_lock
import smartmeal_project.composeapp.generated.resources.ic_person
import smartmeal_project.composeapp.generated.resources.inter_bold
import smartmeal_project.composeapp.generated.resources.pic_logo
import smartmeal_project.composeapp.generated.resources.pic_socials


object LoginScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<LoginViewModel>()
        val navigator = LocalNavigator.currentOrThrow

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
            Image(
                painter = painterResource(Res.drawable.pic_logo),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            Text(
                text = "Sign In",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Colors.Primary
            )

            Spacer(modifier = Modifier.height(45.dp))

            CustomTextField(
                value = state.emailorusername,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = "Email or User Name",
                leadingIcon = Res.drawable.ic_person
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomPasswordField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it)},
                placeholder = "Password",
                leadingIcon = Res.drawable.ic_lock,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Forget Password?",
                fontWeight = FontWeight.Bold,
                color = Colors.Primary,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.End,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomButtonField(
                text = "Sign In",
                onClick = { viewModel.LoginSignIn()},
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(60.dp))

            Image(
                painter = painterResource(Res.drawable.pic_socials),
                contentDescription = null,
                modifier = Modifier
                    .height(92.dp)
                    .width(272.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Don't have account?",
                    fontSize = 15.sp,
                    color = Colors.Primary
                )

                Text(
                    text = "Sign Up",
                    fontSize = 15.sp,
                    color = Colors.Primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { onRegisterClick() }
                )
            }
        }
    }
}
