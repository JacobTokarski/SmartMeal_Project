package org.example.smartmeal.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

// - ewentaulnie do korekty

@Composable
fun CustomSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: DrawableResource? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,

        placeholder = {
            Text(
                text = placeholder,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold //
            )
        },

        leadingIcon = leadingIcon?.let { icon ->
            {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = Color.White //
                )
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Colors.Primary, //
            unfocusedContainerColor = Colors.Primary, //
            focusedIndicatorColor = Colors.Primary, //
            focusedContainerColor = Colors.Primary //
        )
    )
}