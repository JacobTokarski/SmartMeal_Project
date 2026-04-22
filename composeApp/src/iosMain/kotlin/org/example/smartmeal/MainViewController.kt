package org.example.smartmeal

import androidx.compose.ui.window.ComposeUIViewController
import org.example.smartmeal.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    })
{
    App()
}