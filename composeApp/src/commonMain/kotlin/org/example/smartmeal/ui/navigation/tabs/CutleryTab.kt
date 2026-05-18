package org.example.smartmeal.ui.navigation.tabs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.example.smartmeal.ui.views.cutlery.CutleryContent
import org.example.smartmeal.ui.views.cutlery.CutleryScreen
import org.example.smartmeal.ui.views.cutlery.CutleryViewModel
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_cutlery_bottom
import smartmeal_project.composeapp.generated.resources.ic_lock

object CutleryTab: Tab {
    @Composable
    override fun Content() {
        CutleryScreen().Content()
    }

    override val options: TabOptions
    @Composable
    get() {
        val title = "Jadłospis"
        val icon = painterResource(Res.drawable.ic_cutlery_bottom)

        return remember{
            TabOptions(
                index = 1u,
                title = title,
                icon = icon
            )
        }
    }
}