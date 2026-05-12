package org.example.smartmeal.ui.views.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.example.smartmeal.ui.navigation.tabs.AITab
import org.example.smartmeal.ui.navigation.tabs.CutleryTab
import org.example.smartmeal.ui.navigation.tabs.HealthTab
import org.example.smartmeal.ui.navigation.tabs.HomeTab
import org.example.smartmeal.ui.navigation.tabs.ProfileTab
import org.example.smartmeal.ui.theme.Colors

object MainScreen: Screen {
    @Composable
    override fun Content() {
        TabNavigator(HomeTab) { tabNavigator ->
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        containerColor = Color.White,
                        tonalElevation = 0.dp
                    ) {
                        TabNavigationItem(CutleryTab)
                        TabNavigationItem(HealthTab)
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(AITab)
                        TabNavigationItem(ProfileTab)
                    }
                }
            ) { paddingValues ->
                Box(Modifier.padding(paddingValues)) {
                    CurrentTab()
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {

    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current == tab
    val contentColor = if (isSelected) Colors.Primary else Colors.NotSelected

    NavigationBarItem(
        selected = isSelected,
        onClick = { tabNavigator.current = tab },
        icon = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                   painter = tab.options.icon!!,
                    contentDescription = tab.options.title,
                    modifier = Modifier
                        .size(30.dp),
                    tint = contentColor
                )

                if (isSelected) {

                    Spacer(modifier = Modifier.height(4.dp))

                    Canvas(
                        modifier = Modifier
                            .width(20.dp)
                            .height(2.dp)
                    ) {
                        drawRoundRect(
                            color = Colors.Primary,
                            cornerRadius = CornerRadius(2f, 2f)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        },

        label = {
            Text(
                text = tab.options.title,
                fontSize = 12.sp,
                color = contentColor,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
            )
        },
        alwaysShowLabel = false,
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent
        )
    )
}