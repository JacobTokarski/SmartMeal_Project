package org.example.smartmeal.ui.views.login.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.components.CustomButtonField
import org.example.smartmeal.ui.components.CustomPasswordField
import org.example.smartmeal.ui.components.CustomEmailField
import org.example.smartmeal.ui.theme.Colors
import org.example.smartmeal.ui.utils.AuthError
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_lock
import smartmeal_project.composeapp.generated.resources.ic_person
import smartmeal_project.composeapp.generated.resources.pic_socials

@Composable
fun LoginForm(
    email: String,
    password: String,
    emailError: AuthError,
    passwordError: AuthError,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    CustomEmailField(
        value = email,
        onValueChange = onEmailChange,
        placeholder = "Email",
        leadingIcon = Res.drawable.ic_person,
        error = emailError
    )

    Spacer(modifier = Modifier.height(20.dp))

    CustomPasswordField(
        value = password,
        onValueChange = onPasswordChange,
        placeholder = "Password",
        leadingIcon = Res.drawable.ic_lock,
        isPassword = true,
        error = passwordError
    )

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = "Forget Password?",
        fontWeight = FontWeight.Bold,
        color = Colors.Primary,
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.End,
        fontSize = 15.sp
    )

    Spacer(modifier = Modifier.height(25.dp))

    CustomButtonField(
        text = "Sign In",
        onClick = onLoginClick,
        modifier = Modifier
    )

    Spacer(modifier = Modifier.height(45.dp))

    Image(
        painter = painterResource(Res.drawable.pic_socials),
        contentDescription = null,
        modifier = Modifier
            .height(92.dp)
            .width(272.dp)
    )
}