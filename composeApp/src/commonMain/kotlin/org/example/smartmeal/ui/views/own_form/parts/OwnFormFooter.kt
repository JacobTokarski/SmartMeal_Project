package org.example.smartmeal.ui.views.own_form.parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.pic_camera

@Composable
fun OwnFormFooter(

) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(156.dp)
            .clickable{} //
            .clip(RoundedCornerShape(16.dp)),
        color = Colors.Secondary_Form,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(Res.drawable.pic_camera),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "+ Dodaj zdjęcie",
                fontSize = 15.sp,
            )
        }
    }
}