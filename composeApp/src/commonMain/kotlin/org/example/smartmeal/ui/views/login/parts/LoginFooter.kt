package org.example.smartmeal.ui.views.login.parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors

@Composable
fun LoginFooter(
    onRegisterClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "Don't have account?",
            fontSize = 15.sp,
            color = Colors.Primary
        )

        Text(
            text = "Sign Up",
            fontSize = 15.sp,
            color = Colors.Primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable { onRegisterClick() }
        )
    }
}