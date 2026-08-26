package org.example.smartmeal.model.health

import kotlin.math.round

enum class Gender { MALE, FEMALE}

enum class ActivityLevel(
    val multiplayer: Double,
    val label: String,
    val description: String,
) {
    NONE(1.2, "Brak aktywności", "Praca siedząca, brak dodatkowych ćwiczeń, bardzo mało ruchu w ciągu dnia."),
    LOW(1.375, "Niska aktywność", "Spacerowanie, lekkie prace domowe, 1-2 dni w tygodniu z lekkim treningiem."),
    MODERATE(1.55, "Średnia aktywność", "Regularne treningi 3-4 razy w tygodniu (np. siłownia/bieganie) lub praca fizyczna"),
    HIGH(1.725, "Wysoka aktywność", "Codzienne intensywne treningi lub ciężka fizyczna praca"),
    VERY_HIGH(1.9, "Bardzo wysoka aktywność", "Sportowcy zawodowi, codzienne ciężkie treningi")
}

fun calculateBMI(
    weightKg: Double,
    heightCm: Double,
): Double {
    val heightM = heightCm / 100.0
    return weightKg / ( heightM * heightM)
}

fun calculateBMR(
    gender: Gender,
    weightKg: Double,
    heightCm: Double,
    age: Int,
): Double {
    val base = 10 * weightKg + 6.25 * heightCm - 5 * age
    return if (gender == Gender.MALE) base + 5 else base - 161
}

fun calculateTDEE(
    bmr: Double,
    activityLevel: ActivityLevel
): Double {
    return bmr * activityLevel.multiplayer
}

fun formatOneDecimal(value: Double): String {
    val rounded = round(value * 10) / 10.0
    return rounded.toString()
}