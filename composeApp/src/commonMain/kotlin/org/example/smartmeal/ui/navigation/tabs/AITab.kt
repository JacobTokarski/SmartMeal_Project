package org.example.smartmeal.ui.navigation.tabs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_lock

object AITab: Tab {
    @Composable
    override fun Content() {
        Text(
           text = "AI just for now"
        )
    }

    override val options: TabOptions
    @Composable
    get() {
        val title = "AI"
        val icon = painterResource(Res.drawable.ic_lock)

        return remember {
            TabOptions(
                index = 4u,
                title = title,
                icon = icon
            )
        }
    }
}