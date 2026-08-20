package org.example.smartmeal.ui.views.bmi_form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.ui.views.bmi_form.parts.BmiFormBody
import org.example.smartmeal.ui.views.bmi_form.parts.BmiFormFooter
import org.example.smartmeal.ui.views.bmi_form.parts.BmiFormHeader

// Plik, który będzie zawierał formularz umożliwiający wyliczenie BMI

class BmiFormScreen: Screen {
    @Composable
    override fun Content() {
        BmiFormContent(
            onCancelClick = {},
            onSaveClick = {}
        )
    }
}

@Composable
fun BmiFormContent(
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BmiFormHeader()

        BmiFormBody(
            height = height,
            onHeightChange = { height = it },
            weight = weight,
            onWeightChange = { weight = it }
        )

        BmiFormFooter(
            onCancelClick = onCancelClick,
            onSaveClick = onSaveClick
        )
    }
}