package org.example.smartmeal.ui.components.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.model.chat.ChatMessage
import org.example.smartmeal.ui.theme.Colors

@Composable
fun CustomMessageBubble(
    message: ChatMessage
) {
    val isBot = message.role == ChatMessage.Role.ASSISTANT

    val bubbleShape = RoundedCornerShape(
        topStart = 14.dp,
        topEnd = 14.dp,
        bottomStart = if (isBot) 2.dp else 14.dp,
        bottomEnd = if (isBot) 14.dp else 2.dp,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp),
        contentAlignment = if (isBot) Alignment.CenterStart else Alignment.CenterEnd
    ) {
        Text(
            text = message.content,
            color = if (isBot) Color(0xFF1A3B2E) else Color(0xFF222222),
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .widthIn(280.dp)
                .background(
                    color = if (isBot) Color(0xFFE3F1EA) else Color(0xFFF0F0F0),
                )
                .border(
                    width = 1.dp,
                    color = if (isBot) Colors.Primary.copy(alpha = 0.35f) else Color.Black.copy(alpha = 0.15f),
                    shape = bubbleShape
                )
                .padding(horizontal = 15.dp, vertical = 10.dp)
        )
    }
}