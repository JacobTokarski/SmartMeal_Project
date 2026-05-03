package org.example.smartmeal.ui.views.register.parts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.smartmeal.ui.components.CustomButtonField
import org.example.smartmeal.ui.components.CustomEmailField
import org.example.smartmeal.ui.components.CustomPasswordField
import org.example.smartmeal.ui.utils.AuthError
import org.example.smartmeal.ui.views.register.RegisterUIState
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_lock
import smartmeal_project.composeapp.generated.resources.ic_person

@Composable

fun RegisterForm(
    state: RegisterUIState,
    username: String,
    onNicknameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    confirmEmail: String,
    onEmailConfirm: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onPasswordConfirm: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    CustomEmailField(
        value = username,
        onValueChange = onNicknameChange,
        placeholder = "Username",
        leadingIcon = Res.drawable.ic_person,
        error = state.usernameError
    )

    Spacer(modifier = Modifier.height(30.dp))

    CustomEmailField(
        value = email,
        onValueChange = onEmailChange,
        placeholder = "Email",
        leadingIcon = Res.drawable.ic_person,
        error = state.emailError
    )

    Spacer(modifier = Modifier.height(30.dp))

    CustomEmailField(
        value = confirmEmail,
        onValueChange = onEmailConfirm,
        placeholder = "Confirm Email",
        leadingIcon = Res.drawable.ic_person,
        error = state.confirmEmailError
    )

    Spacer(modifier = Modifier.height(30.dp))

    CustomPasswordField(
        value = password,
        onValueChange = onPasswordChange,
        placeholder = "Password",
        leadingIcon = Res.drawable.ic_lock,
        isPassword = true,
        error = state.passwordError
    )

    Spacer(modifier = Modifier.height(30.dp))

    CustomPasswordField(
        value = confirmPassword,
        onValueChange = onPasswordConfirm,
        placeholder = "Confirm Password",
        leadingIcon = Res.drawable.ic_lock,
        isPassword = true,
        error = state.confirmPasswordError
    )

    Spacer(modifier = Modifier.height(60.dp))

    CustomButtonField(
        text = "Sign Up",
        onClick = onRegisterClick,
        modifier = Modifier
    )

    Spacer(modifier = Modifier.height(60.dp))
}