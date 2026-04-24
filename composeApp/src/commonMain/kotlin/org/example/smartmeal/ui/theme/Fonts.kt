package org.example.smartmeal.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.inter_bold
import smartmeal_project.composeapp.generated.resources.inter_regular
import smartmeal_project.composeapp.generated.resources.inter_semibold

@Composable

fun interFonts() = FontFamily(
    Font(Res.font.inter_regular, FontWeight.Normal),
    Font(Res.font.inter_semibold, FontWeight.SemiBold),
    Font(Res.font.inter_bold, FontWeight.Bold)
)


@Composable
fun smartMealTypography() = Typography(

    bodyMedium = TextStyle(
        fontFamily = interFonts(),
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal
    ),

    headlineMedium = TextStyle(
        fontFamily = interFonts(),
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold
    ),

    headlineLarge = TextStyle(
        fontFamily = interFonts(),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    ),
)