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
import org.example.smartmeal.model.health.calculateBMI
import kotlin.time.Clock

data class BmiFormUIState (
    val height: String = "",
    val weight: String = "",
    val isSaved: Boolean = false
)
class BmiFormViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(BmiFormUIState())

    val uiState: StateFlow<BmiFormUIState> = _uiState.asStateFlow()

    fun onHeightChange(value: String) = _uiState.update { it.copy(height = value) }
    fun onWeightChange(value: String) = _uiState.update { it.copy(weight = value) }

    fun onAcceptClick() {
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