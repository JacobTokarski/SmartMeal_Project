package org.example.smartmeal.ui.components.health.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import org.example.smartmeal.model.health.formatPolishDate
import kotlin.math.round

@Composable
fun CustomHealthTDEECard(
    onClick: () -> Unit,
    date: LocalDate,
    tdee: Double,
) {

    val tdeeRounded = round(tdee).toInt()
    val lossRange = "${tdeeRounded - 500} - ${tdeeRounded - 300} kcal"
    val maintenanceRange = "${tdeeRounded - 100} - ${tdeeRounded + 100} kcal"

    OutlinedCard(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White
        ),
        border = CardDefaults.outlinedCardBorder(enabled = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            Text(
                text = "$tdeeRounded kcal",
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Dzienne zapotrzebowanie kaloryczne (TDEE)",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF616161)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Utrata wagi:",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(3.dp))

                Text(
                    text = lossRange,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Utrzymanie wagi:",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(3.dp))

                Text(
                    text = maintenanceRange,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Ostatni pomiar: ${formatPolishDate(date)}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }
    }
}