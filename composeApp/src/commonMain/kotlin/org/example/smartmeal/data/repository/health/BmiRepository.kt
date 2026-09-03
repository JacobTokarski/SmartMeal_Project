package org.example.smartmeal.data.repository.health

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.smartmeal.model.health.BmiEntry
import kotlin.collections.emptyList

object BmiRepository {
    private val _current = MutableStateFlow<BmiEntry?>(null)

    val current: StateFlow<BmiEntry?> = _current.asStateFlow()

    private val _history = MutableStateFlow<List<BmiEntry>>(emptyList())

    val history: StateFlow<List<BmiEntry>> = _history.asStateFlow()

    fun saveNewCalculation(entry: BmiEntry) {
        _current.value?.let { old -> _history.value += old }
        _current.value = entry
    }
}