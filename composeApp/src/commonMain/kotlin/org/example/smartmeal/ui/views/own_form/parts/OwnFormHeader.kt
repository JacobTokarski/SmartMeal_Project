package org.example.smartmeal.ui.views.own_form.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors

@Composable
fun OwnFormHeader(
    title: String,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .statusBarsPadding(),
        color = Colors.Secondary_Form,
        shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
    }
}