package org.example.smartmeal.model.health

import kotlinx.datetime.LocalDate

data class BmiEntry(
    val id: String,
    val date: LocalDate,
    val weightKg: Double,
    val heightCm: Double,
    val bmi: Double,
    val category: String,
)