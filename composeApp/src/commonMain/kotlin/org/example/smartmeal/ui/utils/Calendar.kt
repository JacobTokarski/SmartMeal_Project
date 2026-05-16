package org.example.smartmeal.ui.utils

import kotlinx.datetime.LocalDate

data class CalendarDay(
    val date: LocalDate,
    val dayName: String,
    val dayNumber: String,
    val isToday: Boolean,
)
