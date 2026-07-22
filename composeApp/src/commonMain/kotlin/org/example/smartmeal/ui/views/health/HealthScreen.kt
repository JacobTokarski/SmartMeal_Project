package org.example.smartmeal.ui.views.health

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.ui.views.health.parts.HealthEmptyState
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
    HealthEmptyState()
}