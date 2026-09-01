package org.example.smartmeal.ui.components.health.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import org.example.smartmeal.model.health.BmiCategory
import org.example.smartmeal.model.health.formatOneDecimal
import org.example.smartmeal.model.health.formatPolishDate

// Komponent ten będzie reprezentował wizualną reprezentację wskaźnika BMI na ekranie "Główna"

@Composable
fun CustomHealthBMICard(
    onClick: () -> Unit,
    bmi: Double,
    date: LocalDate,
    modifier: Modifier = Modifier
) {

    val category = BmiCategory.fromBMI(bmi)

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
                .padding(horizontal = 15.dp, vertical = 10.dp),
        ) {
            Row(
              modifier = Modifier
                  .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formatOneDecimal(bmi),
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    color = Color.Black
                )

                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp)),
                    color = category.color.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = category.label,
                        fontSize = 15.sp, //
                        color = category.color,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Wskaźnik masy ciała (BMI)",
                fontSize = 15.sp, //
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomBMIScaleBar(
                bmi = bmi,
                modifier = modifier
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Ostatni pomiar: ${formatPolishDate(date)}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                color = Color.LightGray
            )
        }
    }
}
