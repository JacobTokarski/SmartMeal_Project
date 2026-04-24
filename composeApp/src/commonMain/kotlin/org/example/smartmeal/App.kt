package org.example.smartmeal

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.example.smartmeal.ui.theme.fonts.SmartMealTheme
import org.example.smartmeal.ui.views.login.LoginScreen

@Composable
fun App() {
    SmartMealTheme {
        Navigator(LoginScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}