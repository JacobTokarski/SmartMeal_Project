package org.example.smartmeal.ui.components.chat

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.smartmeal.model.chat.ChatMessage

@Composable
fun CustomMessageList(
    messages: List<ChatMessage>,
    isBotTyping: Boolean,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        if (isBotTyping) {
            item(key = "typing-indicator") {
                CustomChatIndicator()
            }
        }

        items(
            items = messages.reversed(),
            key = { it.id}
        ) { message ->
            CustomMessageBubble(message = message)
        }
    }
}