package org.example.smartmeal.ui.theme.fonts

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun SmartMealTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = SmartMealTypography(),
        content = content
    )
}