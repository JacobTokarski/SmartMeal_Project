package org.example.smartmeal.ui.views.bmi_form.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.smartmeal.ui.components.health.CustomFormAcceptButton
import org.example.smartmeal.ui.components.health.CustomFormCancelButton

@Composable
fun BmiFormFooter(
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomFormCancelButton(
            text = "Anuluj",
            onClick = onCancelClick,
            enabled = true
        )

        CustomFormAcceptButton(
            text = "Zapisz",
            onClick = onSaveClick,
            enabled = true
        )
    }
}