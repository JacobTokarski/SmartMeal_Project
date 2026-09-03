package org.example.smartmeal.ui.views.tdee_form.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.smartmeal.ui.components.health.CustomFormAcceptButton
import org.example.smartmeal.ui.components.health.CustomFormCancelButton

@Composable
fun TdeeFormFooter(
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