package org.example.smartmeal.data.remote

import org.example.smartmeal.model.chat.ChatMessage

actual fun createAiRepository(): AiRepository = object : AiRepository {
    override suspend fun ask(history: List<ChatMessage>, prompt: String): AiResult =
        AiResult.Failure("Asystent AI na ten moment jest dostępny tylko na Androidzie")
}