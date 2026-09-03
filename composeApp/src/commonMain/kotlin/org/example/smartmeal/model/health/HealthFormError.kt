package org.example.smartmeal.model.health


sealed class HealthFormError {
    object None: HealthFormError()
    object EmptyField: HealthFormError()
    object InvalidNumberFormat: HealthFormError()
    object ValueOutOfRange: HealthFormError()
}

fun HealthFormError.asString(): String = when (this) {
    is HealthFormError.None -> ""
    is HealthFormError.EmptyField -> "Field cannot be empty!"
    is HealthFormError.InvalidNumberFormat -> "Field cannot contain letters!"
    is HealthFormError.ValueOutOfRange -> "Field is out of range!"
}