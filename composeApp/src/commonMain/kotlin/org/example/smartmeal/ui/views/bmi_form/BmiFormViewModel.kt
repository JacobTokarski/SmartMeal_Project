package org.example.smartmeal.ui.views.bmi_form

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate
import org.example.smartmeal.data.repository.health.BmiRepository
import org.example.smartmeal.model.health.BmiCategory
import org.example.smartmeal.model.health.BmiEntry
import org.example.smartmeal.model.health.HealthFormError
import org.example.smartmeal.model.health.calculateBMI
import kotlin.time.Clock

data class BmiFormUIState (
    val height: String = "",
    val weight: String = "",
    val isSaved: Boolean = false,
    val heightError: HealthFormError = HealthFormError.None,
    val weightError: HealthFormError = HealthFormError.None,
)
class BmiFormViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(BmiFormUIState())

    val uiState: StateFlow<BmiFormUIState> = _uiState.asStateFlow()

    fun onHeightChange(newValue: String) {
        _uiState.value = _uiState.value.copy(height = newValue, heightError = HealthFormError.None)
    }

    fun onWeightChange(newValue: String) {
        _uiState.value = _uiState.value.copy(weight = newValue, weightError = HealthFormError.None)
    }

    fun onAcceptClick() {

        val currentState = _uiState.value

        val heightNumber = currentState.height.replace(',', '.').toDoubleOrNull()
        val heightError = when {
            currentState.height.isBlank() -> HealthFormError.EmptyField
            heightNumber == null -> HealthFormError.InvalidNumberFormat
            heightNumber !in 50.0..250.0 -> HealthFormError.ValueOutOfRange
            else -> HealthFormError.None
        }

        val weightNumber = currentState.weight.replace(',', '.').toDoubleOrNull()
        val weightError = when {
            currentState.weight.isBlank() -> HealthFormError.EmptyField
            weightNumber == null -> HealthFormError.InvalidNumberFormat
            weightNumber !in 20.0..350.0 -> HealthFormError.ValueOutOfRange
            else -> HealthFormError.None
        }

        _uiState.update {
            it.copy(
                heightError = heightError,
                weightError = weightError
            )
        }

        val isDataValid = listOf(heightError, weightError).all { it == HealthFormError.None }

        if (isDataValid) {
            val heightCm = _uiState.value.height.toDoubleOrNull() ?: return
            val weightKg = _uiState.value.weight.toDoubleOrNull() ?: return
            val nowMs = Clock.System.now().toEpochMilliseconds()
            val today = LocalDate.fromEpochDays((nowMs / 86400000).toInt())

            val bmi = calculateBMI(weightKg, heightCm)

            BmiRepository.saveNewCalculation(
                BmiEntry(
                    id = nowMs.toString(),
                    date = today,
                    weightKg = weightKg,
                    heightCm = heightCm,
                    bmi = bmi,
                    category = BmiCategory.fromBMI(bmi).label
                )
            )

            _uiState.update { it.copy(isSaved = true) }
        }
    }
}