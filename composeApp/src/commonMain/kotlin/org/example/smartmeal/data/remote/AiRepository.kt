package org.example.smartmeal.data.remote

import org.example.smartmeal.model.chat.ChatMessage

interface AiRepository {
    suspend fun ask(history: List<ChatMessage>, prompt: String): AiResult
}

sealed class AiResult {
    data class Success(val text: String): AiResult()
    data class Failure(val userMessage: String): AiResult()
}