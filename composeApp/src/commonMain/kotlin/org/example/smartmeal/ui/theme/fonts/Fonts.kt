package org.example.smartmeal.ui.theme.fonts

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.inter_bold
import smartmeal_project.composeapp.generated.resources.inter_regular
import smartmeal_project.composeapp.generated.resources.inter_semibold

@Suppress("ComposableNaming")
@Composable

fun InterFonts() = FontFamily(
    Font(Res.font.inter_regular, FontWeight.Normal),
    Font(Res.font.inter_semibold, FontWeight.SemiBold),
    Font(Res.font.inter_bold, FontWeight.Bold)
)