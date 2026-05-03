package org.example.smartmeal.ui.utils

import androidx.compose.runtime.Composable

sealed class AuthError {
    object None: AuthError()
    object EmptyField: AuthError()
    object InvalidEmailFormat: AuthError()
    object PasswordTooShort: AuthError()
    object MissingUppercase: AuthError()
    object MissingSpecialChar: AuthError()
    object PasswordsDoNotMatch: AuthError()
    object EmailsDoNotMatch: AuthError()
}

@Composable

fun AuthError.asString(): String = when (this) {
    is AuthError.None -> ""
    is AuthError.EmptyField -> "Field cannot be empty!"
    is AuthError.InvalidEmailFormat -> "Your email is missing @"
    is AuthError.PasswordTooShort -> "Password must be at least 6 characters short"
    is AuthError.MissingUppercase -> "Password is missing uppercase"
    is AuthError.MissingSpecialChar -> "Password must contain special char"
    is AuthError.PasswordsDoNotMatch -> "Password do not match"
    is AuthError.EmailsDoNotMatch -> "Email do not match"
}