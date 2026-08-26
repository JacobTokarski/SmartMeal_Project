package org.example.smartmeal.ui.navigation.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.example.smartmeal.ui.theme.Colors
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_health_bottom
import androidx.compose.material3.Tab
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.model.utils.health.HealthSubTabs
import org.example.smartmeal.ui.views.bmi.BmiContent
import org.example.smartmeal.ui.views.bmi_form.BmiFormScreen
import org.example.smartmeal.ui.views.health.HealthContent
import org.example.smartmeal.ui.views.tdee.TdeeContent
import org.example.smartmeal.ui.views.tdee_form.TdeeFormScreen
import org.koin.compose.viewmodel.koinViewModel

object HealthTab : Tab {
    @Composable
    override fun Content() {

        var selectedTab by remember { mutableStateOf(HealthSubTabs.Main) }
        val tabNavigator = LocalNavigator.currentOrThrow
        val rootNavigator = tabNavigator.parent ?: tabNavigator

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            SecondaryScrollableTabRow(
                selectedTabIndex = selectedTab.ordinal,
                containerColor = Color.White,
                indicator = {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier
                            .tabIndicatorOffset(selectedTab.ordinal)
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        color = Colors.Primary,
                        height = 2.dp
                    )
                },
                divider = {}
            ) {
                HealthSubTabs.entries.forEach { tab ->

                    val isSelected = selectedTab == tab

                    Tab(
                        selected = isSelected,
                        onClick = { selectedTab = tab },
                        text = {
                            Text(
                                text = tab.title,
                                modifier = Modifier
                                    .padding(bottom = 4.dp),
                                color = if (isSelected) Colors.Primary else Colors.NotSelected,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                fontSize = 15.sp
                            )
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (selectedTab) {

                    HealthSubTabs.Main -> {
                        HealthContent(viewModel = koinViewModel())
                    }

                    HealthSubTabs.BMI -> {
                        BmiContent(
                            onNavigateToForm = {
                                rootNavigator.push(BmiFormScreen())
                            }
                        )
                    }

                    HealthSubTabs.TDEE -> {
                        TdeeContent(
                            onNavigateToForm = {
                                rootNavigator.push(TdeeFormScreen())
                            }
                        )
                    }
                }
            }
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = "Zdrowie"
            val icon = painterResource(Res.drawable.ic_health_bottom)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }
}