package org.example.smartmeal.ui.components.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.smartmeal.model.chat.ChatSuggestion

@Composable
fun CustomChipSuggestions(
    onSuggestionClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    suggestions: List<ChatSuggestion>
) {
    Row(
        modifier = modifier
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        suggestions.forEach { item ->
            CustomChipButton(
                text = item.label,
                onClick = { onSuggestionClick(item.prompt)},
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}