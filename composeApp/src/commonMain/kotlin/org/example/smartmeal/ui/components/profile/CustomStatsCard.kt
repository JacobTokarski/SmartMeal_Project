package org.example.smartmeal.ui.components.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

// Komponent reprezentujący sekcję statystyk reprezentowanych na ekranie Profilu (aktualny status: Part 1 -> Wartości przypisane na sztywno)

@Composable
fun CustomStatsCard(
    statsName : String,
    statsNumber: String,
    statsIcon: DrawableResource,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .height(170.dp),
        shape = RoundedCornerShape(15.dp),
        color = Color.White,
        border = BorderStroke(1.dp, Colors.Primary)
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .padding(horizontal = 10.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = statsNumber,
                fontSize = 58.sp,
                fontWeight = FontWeight.SemiBold,
                color = Colors.Stats_Number
            )

            Icon(
                painter = painterResource(statsIcon),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp),
                tint = Colors.Primary
            )

            Text(
                text = statsName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Colors.Stats_Number
            )
        }
    }
 }