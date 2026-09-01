package org.example.smartmeal.ui.views.tdee_form

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate
import org.example.smartmeal.data.repository.health.TdeeRepository
import org.example.smartmeal.model.health.Gender
import org.example.smartmeal.model.health.TdeeEntry
import org.example.smartmeal.model.health.calculateBMR
import org.example.smartmeal.model.health.ActivityLevel
import org.example.smartmeal.model.health.HealthFormError
import org.example.smartmeal.model.health.calculateTDEE
import kotlin.time.Clock


data class TdeeFormUiState(
    val age: String = "",
    val height: String = "",
    val weight: String = "",
    val selectedGender: Gender? = null,
    val selectedActivity: ActivityLevel? = null,
    val isSaved: Boolean = false,
    val ageError: HealthFormError = HealthFormError.None,
    val heightError: HealthFormError = HealthFormError.None,
    val weightError: HealthFormError = HealthFormError.None,
    val genderError: HealthFormError = HealthFormError.None,
    val activityError: HealthFormError = HealthFormError.None

)

class TdeeFormViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(TdeeFormUiState())

    val uiState: StateFlow<TdeeFormUiState> = _uiState.asStateFlow()

    fun onAgeChange(newValue: String) {
        _uiState.value = _uiState.value.copy(age = newValue, ageError = HealthFormError.None)
    }

    fun onHeightChange(newValue: String) {
        _uiState.value = _uiState.value.copy(height = newValue, heightError = HealthFormError.None)
    }
    fun onWeightChange(newValue: String) {
        _uiState.value = _uiState.value.copy(weight = newValue, weightError = HealthFormError.None)
    }
    fun onGenderSelect(gender: Gender) {
        _uiState.value = _uiState.value.copy(selectedGender = gender, genderError = HealthFormError.None)
    }
    fun onActivityLevel(level: ActivityLevel) {
        _uiState.value = _uiState.value.copy(selectedActivity = level, activityError = HealthFormError.None)
    }
    
    fun onAcceptClick() {

        val state = uiState.value

        val ageNumber = state.age.toIntOrNull()
        val ageError = when {
            state.age.isBlank() -> HealthFormError.EmptyField
            ageNumber == null -> HealthFormError.InvalidNumberFormat
            ageNumber !in 10..120 -> HealthFormError.ValueOutOfRange
            else -> HealthFormError.None
        }

        val heightNumber = state.height.replace(',', '.').toDoubleOrNull()
        val heightError = when {
            state.height.isBlank() -> HealthFormError.EmptyField
            heightNumber == null -> HealthFormError.InvalidNumberFormat
            heightNumber !in 50.0..250.0 -> HealthFormError.ValueOutOfRange
            else -> HealthFormError.None
        }

        val weightNumber = state.weight.replace(',', '.').toDoubleOrNull()
        val weightError = when {
            state.weight.isBlank() -> HealthFormError.EmptyField
            weightNumber == null -> HealthFormError.InvalidNumberFormat
            weightNumber !in 20.0..350.0 -> HealthFormError.ValueOutOfRange
            else -> HealthFormError.None
        }

        val genderError =
            if (state.selectedGender == null) HealthFormError.EmptyField else HealthFormError.None
        val activityError =
            if (state.selectedActivity == null) HealthFormError.EmptyField else HealthFormError.None

        _uiState.update {
            it.copy(
                ageError = ageError,
                heightError = heightError,
                weightError = weightError,
                genderError = genderError,
                activityError = activityError
            )
        }

        val isDataValid = listOf(ageError, heightError, weightError, genderError, activityError)
            .all { it == HealthFormError.None }

        if (isDataValid) {
            val ageInt = state.age.toIntOrNull() ?: return
            val heightCm = state.height.toDoubleOrNull() ?: return
            val weightKg = state.weight.toDoubleOrNull() ?: return
            val gender = state.selectedGender ?: return
            val activityLevel = state.selectedActivity ?: return

            val nowMs = Clock.System.now().toEpochMilliseconds()
            val today = LocalDate.fromEpochDays((nowMs / 86400000).toInt())

            val bmr = calculateBMR(gender, weightKg, heightCm, ageInt)
            val tdee = calculateTDEE(bmr, activityLevel)

            TdeeRepository.saveNewCalculation(
                TdeeEntry(
                    id = nowMs.toString(),
                    date = today,
                    gender = gender,
                    age = ageInt,
                    heightCm = heightCm,
                    weightKg = weightKg,
                    activity = activityLevel,
                    bmr = bmr,
                    tdee = tdee
                )
            )

            _uiState.update { it.copy(isSaved = true) }
        }
    }
}
