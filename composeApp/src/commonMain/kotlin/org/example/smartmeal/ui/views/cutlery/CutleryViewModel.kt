package org.example.smartmeal.ui.views.cutlery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import org.example.smartmeal.ui.utils.CalendarDay
import kotlin.collections.plus
import kotlin.time.Clock


data class CutleryUIState(
    val test: String, //
)
class CutleryViewModel: ViewModel() {

    val mealCategories = listOf("Śniadanie", "II Śniadanie", "Obiad", "Podwieczorek", "Kolacja")

//    private val today = Clock.System.todayIn(TimeZone.currentSystemDefault())

    private val today = kotlinx.datetime.LocalDate.Companion.fromEpochDays(
        (Clock.System.now().toEpochMilliseconds() / 86400000).toInt()
    )

    var selectedDate by mutableStateOf(today)

    fun getCalendarDays(): List<CalendarDay> {

        val days = mutableListOf<CalendarDay>()
        val startFrom = today.minus(3, DateTimeUnit.DAY)

        repeat(10) { i ->
            val date = startFrom.plus(i, DateTimeUnit.DAY)
            days.add(
                CalendarDay(
                    date = date,
                    dayName = date.dayOfWeek.name.take(2).lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                    dayNumber = date.dayOfMonth.toString(), //
                    isToday = date == today
                )
            )
        }
        return days
    }
}