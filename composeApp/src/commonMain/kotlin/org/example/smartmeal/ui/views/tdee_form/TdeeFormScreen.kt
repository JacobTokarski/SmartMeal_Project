package org.example.smartmeal.ui.views.tdee_form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.MutableStateFlow
import org.example.smartmeal.ui.views.bmi_form.BmiFormViewModel
import org.example.smartmeal.ui.views.tdee_form.parts.TdeeFormBody
import org.example.smartmeal.ui.views.tdee_form.parts.TdeeFormFooter
import org.example.smartmeal.ui.views.tdee_form.parts.TdeeFormHeader
import org.koin.compose.viewmodel.koinViewModel

// Plik, który będzie zawierał formularz umożliwiający wyliczenie TDEE

class TdeeFormScreen: Screen {
    @Composable
    override fun Content() {

        val viewModel = koinViewModel<TdeeFormViewModel>()

        TdeeContent(
            viewModel = viewModel
        )
    }
}


@Composable
fun TdeeContent(
    viewModel: TdeeFormViewModel,
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
        TdeeFormHeader()

        TdeeFormBody(
            age = state.age,
            onAgeChange = viewModel::onAgeChange,
            height = state.height,
            onHeightChange = viewModel::onHeightChange,
            weight = state.weight,
            onWeightChange = viewModel::onWeightChange,
            selectedGender = state.selectedGender,
            onGenderSelect = viewModel::onGenderSelect,
            selectedActivityLevel = state.selectedActivity,
            onActivityLevelSelect = viewModel::onActivityLevel,
        )

        TdeeFormFooter(
            onCancelClick = { navigator.pop() },
            onSaveClick = { viewModel.onAcceptClick() }
        )
    }
}