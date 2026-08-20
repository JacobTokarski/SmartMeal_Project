package org.example.smartmeal.ui.views.bmi_form.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.components.health.CustomFormBmiFields

@Composable
fun BmiFormBody(
    height: String,
    onHeightChange: (String) -> Unit,
    weight: String,
    onWeightChange: (String) -> Unit,
) {
    Column(
       modifier = Modifier
           .fillMaxWidth()
           .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CustomFormBmiFields(
            value = height,
            onValueChange = onHeightChange,
            label = "Wprowadź swój wzrost*",
            placeholder = "178",
            unit = "cm"
        )

        CustomFormBmiFields(
            value = weight,
            onValueChange = onWeightChange,
            label = "Wprowadź swoją wagę*",
            placeholder = "76",
            unit = "kg"
        )
    }

    Spacer(modifier = Modifier.height(30.dp))
}