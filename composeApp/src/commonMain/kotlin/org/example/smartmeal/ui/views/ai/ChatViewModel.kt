package org.example.smartmeal.ui.views.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.smartmeal.data.remote.AiRepository
import org.example.smartmeal.data.remote.AiResult
import org.example.smartmeal.model.chat.ChatMessage
import org.example.smartmeal.model.chat.ChatMessage.Role

data class ChatUiState(
    val messages: List<ChatMessage> = listOf(
        ChatMessage(
            id = "welcome",
            role = Role.ASSISTANT,
            content = "Cześć! Jestem Twoim kulinarnym asystentem. Podaj składniki z lodówki, a zaproponuję gotowe danie.",
            timestamp = 0L
        )
    ),
    val input: String = "",
    val isBotTyping: Boolean = false
) {
    val showSuggestions: Boolean get() = messages.none { it.role == Role.USER}
}


class ChatViewModel(
    private val aiRepository: AiRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())

    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    private var nextId = 0

    fun onInputChange(value: String) = _uiState.update { it.copy(input = value) }

    fun onSuggestionsClick(prompt: String) = _uiState.update { it.copy(input = prompt) }

    fun onSendClick() {

        val state = _uiState.value
        val text = state.input.trim()

        if (text.isEmpty() || state.isBotTyping) return

        val history = state.messages
        val userMessage = ChatMessage("u${nextId++}", Role.USER, text, 0L)

        _uiState.update {
            it.copy(messages = it.messages+ userMessage, input = "", isBotTyping = true)
        }

        viewModelScope.launch {
            val replyText = when (val result = aiRepository.ask(history, text)) {
                is AiResult.Success -> result.text
                is AiResult.Failure -> result.userMessage
            }

            val reply = ChatMessage("a${nextId++}", Role.ASSISTANT, replyText, 0L)
            _uiState.update { it.copy(messages = it.messages+ reply, isBotTyping = false) }
        }
    }
}