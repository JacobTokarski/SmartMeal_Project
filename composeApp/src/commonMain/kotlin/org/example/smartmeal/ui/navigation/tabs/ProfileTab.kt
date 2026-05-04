package org.example.smartmeal.ui.navigation.tabs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_lock
import smartmeal_project.composeapp.generated.resources.ic_profile_bottom

object ProfileTab : Tab {
    @Composable
    override fun Content() {
        Text(
            text = "Profile tab just for now"
        )
    }

    override val options: TabOptions
    @Composable
    get() {
        val title = "Profil"
        val icon = painterResource(Res.drawable.ic_profile_bottom)

        return remember {
            TabOptions(
                index = 5u,
                title = title,
                icon = icon
            )
        }
    }
}