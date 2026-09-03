package org.example.smartmeal.ui.views.health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.example.smartmeal.data.repository.health.BmiRepository
import org.example.smartmeal.data.repository.health.TdeeRepository
import org.example.smartmeal.model.health.BmiEntry
import org.example.smartmeal.model.health.TdeeEntry

data class HealthUIState(
    val bmiEntry: BmiEntry? = null,
    val tdeeEntry: TdeeEntry? = null,
    val isLoading: Boolean = true
) {
    val isEmpty: Boolean get() = bmiEntry == null && tdeeEntry == null
}

class HealthViewModel : ViewModel() {

    val uiState: StateFlow<HealthUIState> = combine(
        BmiRepository.current,
        TdeeRepository.current
    ) { bmi, tdee ->
        HealthUIState(bmiEntry = bmi, tdeeEntry = tdee, isLoading = false)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HealthUIState()
    )
}