package org.example.smartmeal.data.repository.health

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.smartmeal.data.remote.cloud.CloudScope
import org.example.smartmeal.data.remote.cloud.TdeeDoc
import org.example.smartmeal.data.remote.cloud.UserCloud
import org.example.smartmeal.data.remote.cloud.toDoc
import org.example.smartmeal.data.remote.cloud.toDomain
import org.example.smartmeal.model.health.TdeeEntry
import kotlin.collections.plus

object TdeeRepository {
    private val _current = MutableStateFlow<TdeeEntry?>(null)
    val current: StateFlow<TdeeEntry?> = _current.asStateFlow()

    private val _history = MutableStateFlow<List<TdeeEntry>>(emptyList())
    val history: StateFlow<List<TdeeEntry>> = _history.asStateFlow()

    fun saveNewCalculation(entry: TdeeEntry) {
        val old = _current.value
        old?.let { _history.value += it }
        _current.value = entry

        val historyRef = old?.let { UserCloud.document("tdeeHistory", it.id) }
        val currentRef = UserCloud.document("meta", "tdeeCurrent")

        if (old != null) {
            CloudScope.write {
                historyRef?.set(TdeeDoc.serializer(), old.toDoc()) { encodeDefaults = true }
            }
        }
        CloudScope.write {
            currentRef?.set(TdeeDoc.serializer(), entry.toDoc()) { encodeDefaults = true }
        }
    }

    fun clearCurrent() {
        _current.value = null
        val ref = UserCloud.document("meta", "tdeeCurrent")
        CloudScope.write { ref?.delete() }
    }

    suspend fun loadFromCloud() {
        val currentDoc = UserCloud.document("meta", "tdeeCurrent")?.get()
        val historyDocs = UserCloud.collection("tdeeHistory")?.get()?.documents.orEmpty()

        _current.value =
            if (currentDoc != null && currentDoc.exists)
                currentDoc.data(TdeeDoc.serializer()).toDomain()
            else null

        _history.value = historyDocs
            .mapNotNull { it.data(TdeeDoc.serializer()).toDomain() }
            .sortedBy { it.id.toLongOrNull() ?: 0L }
    }

    fun clear() {
        _current.value = null
        _history.value = emptyList()
    }
}