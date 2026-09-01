package org.example.smartmeal.ui.views.health

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.ui.components.health.cards.CustomHealthBMICard
import org.example.smartmeal.ui.components.health.cards.CustomHealthTDEECard
import org.example.smartmeal.ui.views.bmi_form.BmiFormScreen
import org.example.smartmeal.ui.views.health.parts.HealthEmptyState
import org.example.smartmeal.ui.views.tdee_form.TdeeFormScreen
import org.koin.compose.viewmodel.koinViewModel


class HealthScreen: Screen {
    @Composable
    override fun Content() {

        val viewModel = koinViewModel<HealthViewModel>()

        HealthContent(
            viewModel = viewModel
        )
    }
}
@Composable
fun HealthContent(
    viewModel: HealthViewModel
) {
    val state by viewModel.uiState.collectAsState()
    val navigator = LocalNavigator.currentOrThrow
    val rootNavigator = navigator.parent ?: navigator

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 20.dp)
        ) {
            when {
                state.isLoading -> {}

                state.isEmpty -> {
                    HealthEmptyState()
                }
                else -> {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        state.bmiEntry?.let { entry ->
                            CustomHealthBMICard(
                                bmi = entry.bmi,
                                onClick = { rootNavigator.push(BmiFormScreen())},
                                date = entry.date
                            )
                        }

                        state.tdeeEntry?.let { entry ->
                            CustomHealthTDEECard(
                                tdee = entry.tdee,
                                onClick = { rootNavigator.push(TdeeFormScreen())},
                                date = entry.date
                            )
                        }
                    }
                }
            }
        }
    }
}