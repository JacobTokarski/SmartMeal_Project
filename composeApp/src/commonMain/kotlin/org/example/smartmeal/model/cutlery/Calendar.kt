package org.example.smartmeal.model.cutlery

import kotlinx.datetime.LocalDate

data class CalendarDay(
    val date: LocalDate,
    val dayName: String,
    val dayNumber: String,
    val isToday: Boolean,
)
