package org.example.smartmeal.model.errors

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

fun AuthError.asString(): String = when (this) {
    is AuthError.None -> ""
    is AuthError.EmptyField -> "Field cannot be empty!"
    is AuthError.InvalidEmailFormat -> "Your email is missing @"
    is AuthError.PasswordTooShort -> "Password must be at least 6 characters long!"
    is AuthError.MissingUppercase -> "Password is missing uppercase"
    is AuthError.MissingSpecialChar -> "Password must contain special char"
    is AuthError.PasswordsDoNotMatch -> "Passwords do not match"
    is AuthError.EmailsDoNotMatch -> "Emails do not match"
}