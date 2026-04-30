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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .offset(y = 40.dp)
                        .clickable { onBackClick() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow),
                        contentDescription = null,
                        tint = Colors.Primary
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Back",
                        fontSize = 12.sp,
                        color = Colors.Primary
                    )
                }

                Image(
                    painter = painterResource(Res.drawable.pic_reg_leaf),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End)
                        .offset(x = 56.dp)
                        .width(112.dp)
                        .height(200.dp)

                )
            }

            Text(
                text = "Sign Up",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = -(60).dp),
                color = Colors.Primary
            )

            CustomEmailField(
                value = state.username,
                onValueChange = { viewModel.onNicknameChange(it) },
                placeholder = "Username",
                leadingIcon = Res.drawable.ic_person
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomEmailField(
                value = state.email,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = "Email",
                leadingIcon = Res.drawable.ic_person
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomEmailField(
                value = state.confirmEmail,
                onValueChange = { viewModel.onEmailConfirm(it) },
                placeholder = "Confirm Email",
                leadingIcon = Res.drawable.ic_person
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomPasswordField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeholder = "Password",
                leadingIcon = Res.drawable.ic_lock,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomPasswordField(
                value = state.confirmPassword,
                onValueChange = { viewModel.onPasswordConfirm(it) },
                placeholder = "Confirm Password",
                leadingIcon = Res.drawable.ic_lock,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(60.dp))

            CustomButtonField(
                text = "Sign Up",
                onClick = onLoginClick,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(60.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Already have an account?",
                    fontSize = 15.sp,
                    color = Colors.Primary
                )

                Text(
                    text = "Sign In",
                    fontSize = 15.sp,
                    color = Colors.Primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { onLoginClick() }
                )
            }
        }
    }
}