package org.example.smartmeal.data.repository.health

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.smartmeal.model.health.TdeeEntry
import kotlin.collections.plus

object TdeeRepository {
    private val _current = MutableStateFlow<TdeeEntry?>(null)

    val current: StateFlow<TdeeEntry?> = _current.asStateFlow()

    private val _history = MutableStateFlow<List<TdeeEntry>>(emptyList())

    val history: StateFlow<List<TdeeEntry>> = _history.asStateFlow()

    fun saveNewCalculation(entry: TdeeEntry) {
        _current.value?.let { old -> _history.value += old }
        _current.value = entry
    }
}