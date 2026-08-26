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
import org.example.smartmeal.model.health.calculateTDEE
import kotlin.time.Clock


data class TdeeFormUiState(
    val age: String = "",
    val height: String = "",
    val weight: String = "",
    val selectedGender: Gender? = null,
    val selectedActivity: ActivityLevel? = null,
    val isSaved: Boolean = false
)

class TdeeFormViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(TdeeFormUiState())

    val uiState: StateFlow<TdeeFormUiState> = _uiState.asStateFlow()

    fun onAgeChange(value: String) = _uiState.update { it.copy(age = value) }
    fun onHeightChange(value: String) = _uiState.update { it.copy(height = value) }
    fun onWeightChange(value: String) = _uiState.update { it.copy(weight = value) }
    fun onGenderSelect(gender: Gender) = _uiState.update { it.copy(selectedGender = gender) }
    fun onActivityLevel(level: ActivityLevel) = _uiState.update { it.copy(selectedActivity = level) }
    
    fun onAcceptClick() {
        val state = uiState.value
        val ageInt = state.age.toIntOrNull() ?: run { println("BŁĄD: age = '${state.age}'"); return }
        val heightCm = state.height.toDoubleOrNull() ?: run { println("BŁĄD: height = '${state.height}'"); return }
        val weightKg = state.weight.toDoubleOrNull() ?: run { println("BŁĄD: weight = '${state.weight}'"); return }
        val gender = state.selectedGender ?: run { println("BŁĄD: gender = null"); return }
        val activityLevel = state.selectedActivity ?:  run { println("BŁĄD: activity = null"); return }

        println("Wszystkie pola OK, zapisuję...")

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
