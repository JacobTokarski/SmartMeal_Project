package org.example.smartmeal.ui.views.profile.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors

@Composable
fun ProfileHeader(
    displayName: String,
    avatarName: String,
) {
    Surface(
        modifier = Modifier
            .size(120.dp),
        shape = CircleShape,
        shadowElevation = 8.dp,
        color = Colors.Primary,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = avatarName,
                style = MaterialTheme.typography.displayLarge,
                color = Color.White
            )
        }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = "Witaj serdecznie ${displayName}!",
        style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black.copy(alpha = 0.7f),
        )
    )

    Spacer(modifier = Modifier.height(25.dp))
}