package org.example.smartmeal.ui.components.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val Suggestions = listOf(
    "Szybki obiad",
    "Coś na słodko",
    "Potrawa dnia"
)

@Composable
fun CustomChipSuggestions(
    onSuggestionClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    suggestions: List<String> = Suggestions
) {
    Row(
        modifier = modifier
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        suggestions.forEach { suggestions ->
            CustomChipButton(
                text = suggestions,
                onClick = { onSuggestionClick(suggestions)},
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}