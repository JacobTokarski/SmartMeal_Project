package org.example.smartmeal.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors
import org.example.smartmeal.ui.utils.AuthError
import org.example.smartmeal.ui.utils.asString
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_eye
import smartmeal_project.composeapp.generated.resources.ic_eye_off

@Composable
fun CustomPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: DrawableResource? = null,
    isPassword: Boolean = false,
    error: AuthError = AuthError.None

) {
    val isError = error != AuthError.None
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = error.asString(),
                    color = Colors.Error,
                    fontSize = 12.sp
                )
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color.Gray,
                fontSize = 15.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        },

        leadingIcon = leadingIcon?.let { icon ->
            {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Colors.Primary
                )
            }
        },

        trailingIcon = {
            if (isPassword) {

                val icon = if (passwordVisible) Res.drawable.ic_eye else Res.drawable.ic_eye_off

                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = Colors.Primary
                    )
                }
            }
        },

        visualTransformation = if (isPassword && !passwordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,

        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            focusedBorderColor = Colors.Primary,
            unfocusedBorderColor = Colors.Primary,
            errorBorderColor = Colors.Error,
        )
    )
}