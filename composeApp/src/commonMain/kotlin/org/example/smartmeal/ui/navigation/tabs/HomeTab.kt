package org.example.smartmeal.ui.navigation.tabs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_lock
import smartmeal_project.composeapp.generated.resources.ic_recipes_bottom

object HomeTab: Tab {
    @Composable
    override fun Content() {
        Text(
            text = "Smart Meal Main View for now"
        )
    }

    override val options: TabOptions
    @Composable
    get() {
        val title = "Przepisy"
        val icon = painterResource(Res.drawable.ic_recipes_bottom) // Testowo

        return remember {
            TabOptions(
                index = 3u,
                title = title,
                icon = icon
            )
        }
    }
}