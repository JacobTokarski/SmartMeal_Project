package org.example.smartmeal.ui.theme.fonts

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Suppress("ComposableNaming")
@Composable
fun SmartMealTypography() = Typography(

    bodyMedium = TextStyle(
        fontFamily = InterFonts(),
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal
    ),

    headlineMedium = TextStyle(
        fontFamily = InterFonts(),
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold
    ),

    headlineLarge = TextStyle(
        fontFamily = InterFonts(),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    ),
)