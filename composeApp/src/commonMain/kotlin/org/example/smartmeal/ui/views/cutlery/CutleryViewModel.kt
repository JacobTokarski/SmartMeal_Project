package org.example.smartmeal.ui.views.cutlery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import org.example.smartmeal.model.cutlery.CalendarDay
import kotlin.time.Clock

class CutleryViewModel: ViewModel() {

    val mealCategories = listOf("Śniadanie", "II Śniadanie", "Obiad", "Podwieczorek", "Kolacja")

    private val today = kotlinx.datetime.LocalDate.Companion.fromEpochDays(
        (Clock.System.now().toEpochMilliseconds() / 86400000).toInt()
    )

    var selectedDate by mutableStateOf(today)

    private val polishDays = mapOf(
        DayOfWeek.MONDAY to "Pon",
        DayOfWeek.TUESDAY to "Wt",
        DayOfWeek.WEDNESDAY to "Śr",
        DayOfWeek.THURSDAY to "Czw",
        DayOfWeek.FRIDAY to "Pt",
        DayOfWeek.SATURDAY to "Sob",
        DayOfWeek.SUNDAY to "Nd",
    )

    val polishMonthsNominative = mapOf(
        Month.JANUARY to "Styczeń",
        Month.FEBRUARY to "Luty",
        Month.MARCH to "Marzec",
        Month.APRIL to "Kwiecień",
        Month.MAY to "Maj",
        Month.JUNE to "Czerwiec",
        Month.JULY to "Lipiec",
        Month.AUGUST to "Sierpień",
        Month.SEPTEMBER to "Wrzesień",
        Month.OCTOBER to "Październik",
        Month.NOVEMBER to "Listopad",
        Month.DECEMBER to "Grudzień",
    )

     val polishMonthsGenitive = mapOf(
        Month.JANUARY to "stycznia",
        Month.FEBRUARY to "lutego",
        Month.MARCH to "marca",
        Month.APRIL to "kwietnia",
        Month.MAY to "maja",
        Month.JUNE to "czerwca",
        Month.JULY to "lipca",
        Month.AUGUST to "sierpnia",
        Month.SEPTEMBER to "września",
        Month.OCTOBER to "października",
        Month.NOVEMBER to "listopada",
        Month.DECEMBER to "grudnia",
    )

    fun getCalendarDays(): List<CalendarDay> {

        val days = mutableListOf<CalendarDay>()
        val startFrom = today.minus(3, DateTimeUnit.DAY)

        repeat(10) { i ->
            val date = startFrom.plus(i, DateTimeUnit.DAY)
            days.add(
                CalendarDay(
                    date = date,
                    dayName = polishDays[date.dayOfWeek] ?: date.dayOfWeek.name.take(2),
                    dayNumber = date.dayOfMonth.toString(), //
                    isToday = date == today
                )
            )
        }
        return days
    }
}