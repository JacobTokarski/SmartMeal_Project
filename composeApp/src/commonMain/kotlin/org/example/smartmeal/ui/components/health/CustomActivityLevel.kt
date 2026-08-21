package org.example.smartmeal.ui.components.health

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors
import org.example.smartmeal.ui.utils.health.calculatePAL
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_arrow
import smartmeal_project.composeapp.generated.resources.ic_delete_custom

@Composable
fun CustomActivityLevel(
    activityLevel: calculatePAL,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(
        targetValue = if (isSelected) 180f else 0f,
        label = "Test for now"
    )

    Surface(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = RoundedCornerShape(15.dp),
        color = if (isSelected) Colors.Form_Activity_Background else Color.White,
        border = BorderStroke(
            width = if (isSelected) 1.5.dp else 1.dp,
            color = if (isSelected) Colors.Primary else Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = activityLevel.label,
                    fontSize = 15.sp,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    color = if (isSelected) Colors.Primary else Color.Black
                )

                Icon(
                    painter = painterResource(Res.drawable.ic_arrow),
                    contentDescription = null,
                    tint = if (isSelected) Colors.Primary else Color.LightGray,
                    modifier = Modifier
                        .rotate(rotation + 90f)
                )
            }

            if (isSelected) {

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = activityLevel.description,
                    fontSize = 13.sp,
                    color = Colors.Primary.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun ActivityLevelSelector(
    selected: calculatePAL?,
    onSelect: (calculatePAL) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        calculatePAL.entries.forEach { level ->

            CustomActivityLevel(
                activityLevel = level,
                isSelected = level == selected,
                onClick = { onSelect(level) }
            )
        }
    }
}