package org.example.smartmeal.ui.components.health

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors

@Composable
fun CustomFormBmiFields(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String,
    unit: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label?.let { labelText ->
            {
                Text(
                    text = label,
                    fontSize = 15.sp,
                    color = Colors.Text_Form.copy(0.7f),
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        placeholder = {

            Text(
                text = placeholder,
                fontSize = 15.sp,
                color = Colors.Text_Form.copy(0.5f),
                fontWeight = FontWeight.SemiBold
            )
        },
        suffix = {
            Text(
                text = unit,
                color = Colors.Text_Form.copy(0.7f),
                fontSize = 15.sp
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedBorderColor = Colors.Primary,
            focusedBorderColor = Colors.Primary
        )
    )
}