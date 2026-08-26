package org.example.smartmeal.ui.views.bmi_form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.ui.views.bmi_form.parts.BmiFormBody
import org.example.smartmeal.ui.views.bmi_form.parts.BmiFormFooter
import org.example.smartmeal.ui.views.bmi_form.parts.BmiFormHeader
import org.koin.compose.viewmodel.koinViewModel

class BmiFormScreen: Screen {
    @Composable
    override fun Content() {

        val viewModel = koinViewModel<BmiFormViewModel>()

        BmiFormContent(
            viewModel = viewModel
        )
    }
}

@Composable
fun BmiFormContent(
    viewModel: BmiFormViewModel,
) {

    val state by viewModel.uiState.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) navigator.pop()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BmiFormHeader()

        BmiFormBody(
            height = state.height,
            onHeightChange = viewModel::onHeightChange,
            weight = state.weight,
            onWeightChange = viewModel::onWeightChange
        )

        BmiFormFooter(
            onCancelClick = { navigator.pop() },
            onSaveClick = { viewModel.onAcceptClick()}
        )
    }
}