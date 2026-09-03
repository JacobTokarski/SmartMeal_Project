package org.example.smartmeal.ui.components.health.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.model.health.BmiCategory
import org.example.smartmeal.ui.theme.Colors


@Composable
fun CustomBMIScaleBar(
    bmi: Double,
    modifier: Modifier = Modifier,
) {
    val minBmi = 15.0
    val maxBmi = 40.0
    val fraction = ((bmi - minBmi) / (maxBmi - minBmi)).coerceIn(0.0, 1.0)

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
    ) {

        val indicatorOffset =
            maxWidth * fraction.toFloat()

        Column() {
            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset - 6.dp)
                    .size(width = 12.dp, height = 8.dp)
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    val path = Path().apply {
                        moveTo(size.width / 2, size.height)
                        lineTo(0f, 0f)
                        lineTo(size.width, 0f)
                        close()
                    }

                    drawPath(path, Color.Black)

                    //
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
            ) {

                BmiCategory.entries.forEach { category ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(category.color)
                    ) {}
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BmiCategory.entries.forEach { category ->
                    Text(
                        text = if (category.rangeEnd == Double.MAX_VALUE) ">${category.rangeStart}" else "${category.rangeStart}-${category.rangeEnd}", // cała ta linijka tekstu jest do analizy (jak działają te zależności)
                        fontSize = 10.sp,
                        color = Colors.Stats_Number,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}