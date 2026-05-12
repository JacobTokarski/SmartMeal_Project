package org.example.smartmeal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_delete
import smartmeal_project.composeapp.generated.resources.ic_edit
import smartmeal_project.composeapp.generated.resources.pic_burger
import smartmeal_project.composeapp.generated.resources.pic_camera

@Composable
fun CustomRecipeCard(
    title: String,
    hasImage: Boolean = false,
    calories: String = "1500 kcal", // dane na razie przypisane statycznie, potem będzie pobierać je z formularza
    time: String = "35 minut",  // dane na razie przypisane statycznie, potem będzie pobierać je z formularza
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .width(168.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.85f) //
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .then(
                    if (!hasImage) Modifier.border(1.dp, Colors.Primary, RoundedCornerShape(15.dp))
                    else Modifier
                )
        ) {
            if (hasImage) {
                Image(
                    painter = painterResource(Res.drawable.pic_burger),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Icon(
                    painter = painterResource(Res.drawable.pic_camera),
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .align(Alignment.Center),
                    tint = Color.Black
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val iconBackground =
                    if (hasImage) Color.White.copy(alpha = 0.8f) else Color.Transparent

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(iconBackground)
                        .clickable { onEditClick() }, //
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_edit),
                        contentDescription = "Edit icon",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(iconBackground)
                        .clickable { onDeleteClick() }, //
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_delete),
                        contentDescription = "Delete icon",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }

            if (hasImage) {

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .clip(RoundedCornerShape(topEnd = 15.dp))
                        .background(Color.White.copy(alpha = 0.85f))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "$calories $time",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }

            } else {

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .clip(RoundedCornerShape(topEnd = 15.dp))
                        .background(Color.Black.copy(alpha = 0.85f))
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "$calories $time",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}