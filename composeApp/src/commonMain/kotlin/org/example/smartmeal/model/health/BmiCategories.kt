package org.example.smartmeal.model.health

import androidx.compose.ui.graphics.Color

enum class BmiCategory(
    val label: String,
    val rangeStart: Double,
    val rangeEnd: Double,
    val color: Color,
) {
    UNDERWEIGHT("Niedowaga", 0.0, 18.5, Color(0xFF87ACF3)),
    NORMAL("Waga prawidłowa", 18.5, 25.0, Color(0xFF76C113)),
    OVERWEIGHT("Nadwaga", 25.0, 30.0, Color(0xFFEF9F27)),
    OBESE_I("Otyłość I stopnia", 30.0, 35.0, Color(0xFFDE4D1F)),
    OBESE_II("Otyłość II stopnia", 35.0, Double.MAX_VALUE, Color(0xFFD40F0E));

    companion object {
        fun fromBMI(
            bmi: Double,
        ): BmiCategory =
            entries.first { bmi >= it.rangeStart && bmi < it.rangeEnd
        }
    }
}