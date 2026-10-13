package org.example.smartmeal.ui.views.own_form.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.components.health.CustomFormAcceptButton
import org.example.smartmeal.ui.components.health.CustomFormCancelButton
import org.example.smartmeal.ui.theme.Colors
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.pic_camera

@Composable
fun OwnFormFooter(
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit,
    onAddImageClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color(0xFFF6F8F7)) // Kolor na razie na próbę jakikolwiek, potem dostosujemy
                .drawBehind {
                    val stroke = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
                    )
                    drawRoundRect(
                        color = Color.LightGray.copy(alpha = 0.8f),
                        cornerRadius = CornerRadius(16.dp.toPx()),
                        style = stroke
                    )
                }
                .clickable { onAddImageClick() },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.pic_camera),
                    contentDescription = null,
                    tint = Colors.Text_Form.copy(alpha = 0.6f),
                    modifier = Modifier
                        .size(54.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Wybierz zdjęcie z galerii",
                    fontSize = 13.sp,
                    color = Colors.Text_Form.copy(alpha = 0.6f),
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomFormCancelButton(
                text = "Anuluj",
                onClick = onBackClick,
            )

            CustomFormAcceptButton(
                text = "Zapisz",
                onClick = onSaveClick,
            )
        }
    }
}