package org.example.smartmeal.ui.components.health

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors

// Komponent odpowiedzialny za przycisk "Oblicz BMI/Tdee", który przenosi nas do formularzy

@Composable
fun CustomFormButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,

) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(43.dp) //
            .width(110.dp), //
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.Primary,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}