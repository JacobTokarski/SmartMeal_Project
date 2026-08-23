package org.example.smartmeal.model.health

import kotlinx.datetime.LocalDate

data class TdeeEntry(
    val id: String,
    val date: LocalDate,
    val gender: Gender,
    val age: Int,
    val heightCm: Double,
    val weightKg: Double,
    val activity: calculatePAL,
    val bmi: Double,
    val tdee: Double,
)