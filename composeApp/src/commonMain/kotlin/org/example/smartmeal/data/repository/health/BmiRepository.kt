package org.example.smartmeal.data.repository.health

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.smartmeal.data.remote.cloud.BmiDoc
import org.example.smartmeal.data.remote.cloud.CloudScope
import org.example.smartmeal.data.remote.cloud.UserCloud
import org.example.smartmeal.data.remote.cloud.toDoc
import org.example.smartmeal.data.remote.cloud.toDomain
import org.example.smartmeal.model.health.BmiEntry
import kotlin.collections.emptyList

object BmiRepository {
    private val _current = MutableStateFlow<BmiEntry?>(null)

    val current: StateFlow<BmiEntry?> = _current.asStateFlow()

    private val _history = MutableStateFlow<List<BmiEntry>>(emptyList())

    val history: StateFlow<List<BmiEntry>> = _history.asStateFlow()

    fun saveNewCalculation(entry: BmiEntry) {
        val old = _current.value
        old?.let { _history.value += it }
        _current.value = entry

        val historyRef = old?.let { UserCloud.document("bmiHistory", it.id)}
        val currentRef = UserCloud.document("meta", "bmiCurrent")

        if (old != null) {
            CloudScope.write {
                historyRef?.set(BmiDoc.serializer(), old.toDoc()) { encodeDefaults = true }
            }
        }
        CloudScope.write {
            currentRef?.set(BmiDoc.serializer(), entry.toDoc()) { encodeDefaults = true }
        }
    }

    fun clearCurrent() {
        _current.value = null
        val ref = UserCloud.document("meta", "bmiCurrent")
        CloudScope.write { ref?.delete() }
    }

    suspend fun loadFromCloud() {
        val currentDoc = UserCloud.document("meta", "bmiCurrent")?.get()
        val historyDocs = UserCloud.collection("bmiHistory")?.get()?.documents.orEmpty()

        _current.value =
            if (currentDoc != null && currentDoc.exists)
                currentDoc.data(BmiDoc.serializer()).toDomain()
            else null

        _history.value = historyDocs
            .mapNotNull { it.data(BmiDoc.serializer()).toDomain() }
            .sortedBy { it.id.toLongOrNull() ?: 0L }
    }

    fun clear() {
        _current.value = null
        _history.value = emptyList()
    }
}