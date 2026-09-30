package org.example.smartmeal.model.chat

data class ChatMessage(
    val id: String,
    val role: Role,
    val content: String,
    val timestamp: Long
) {
    enum class Role { USER, ASSISTANT }
}