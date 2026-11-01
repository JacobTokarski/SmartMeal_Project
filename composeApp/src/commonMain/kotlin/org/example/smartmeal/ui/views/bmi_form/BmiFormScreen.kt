package org.example.smartmeal.ui.views.bmi_form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.ui.views.bmi_form.parts.BmiFormBody
import org.example.smartmeal.ui.views.bmi_form.parts.BmiFormFooter
import org.example.smartmeal.ui.views.bmi_form.parts.BmiFormHeader
import org.koin.compose.viewmodel.koinViewModel

class BmiFormScreen : Screen {
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
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) navigator.pop()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BmiFormHeader(
            onDeleteClick = if (state.isEditMode) { { showDeleteDialog = true } } else null
        )

        BmiFormBody(
            height = state.height,
            onHeightChange = viewModel::onHeightChange,
            weight = state.weight,
            onWeightChange = viewModel::onWeightChange,
            weightError = state.weightError,
            heightError = state.heightError

        )

        BmiFormFooter(
            onCancelClick = { navigator.pop() },
            onSaveClick = { viewModel.onAcceptClick() }
        )

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Usunąć pomiar?") },
                text = { Text("Ta operacja bezpowrotnie wyczyści Twój aktualny wynik BMI.") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDeleteDialog = false
                            viewModel.onDeleteClick()
                        }
                    ) {
                        Text("Usuń", color = Color.Red, fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text("Anuluj", color = Color.Black)
                    }
                }
            )
        }
    }
}