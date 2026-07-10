package org.example.smartmeal.ui.views.register.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_arrow
import smartmeal_project.composeapp.generated.resources.pic_reg_leaf

@Composable
fun RegisterHeader(
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .clickable { onBackClick() }
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow),
                    contentDescription = null,
                    tint = Colors.Primary
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "Cofnij",
                    fontSize = 15.sp,
                    color = Colors.Primary
                )
            }

            Image(
                painter = painterResource(Res.drawable.pic_reg_leaf),
                contentDescription = null,
                contentScale = ContentScale.None,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 55.dp, y = 20.dp)
                    .width(112.dp)
                    .height(220.dp)
            )
        }

        Text(
            text = "Zarejestruj się",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = -(25).dp),
            color = Colors.Primary
        )
    }
}