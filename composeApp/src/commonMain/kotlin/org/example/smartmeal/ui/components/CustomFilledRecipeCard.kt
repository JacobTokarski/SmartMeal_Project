package org.example.smartmeal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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
import smartmeal_project.composeapp.generated.resources.ic_delete_custom
import smartmeal_project.composeapp.generated.resources.pic_burger
import smartmeal_project.composeapp.generated.resources.pic_camera


@Composable
fun CustomFilledRecipeCard(
    mealName: String,
    recipeTitle: String,
    calories: String,
    time: String,
    hasImage: Boolean,
    onDeleteClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                if (hasImage) {
                    Image(
                        painter = painterResource(Res.drawable.pic_burger),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                } else {
                    Icon(
                        painter = painterResource(Res.drawable.pic_camera),
                        contentDescription = null,
                        modifier = Modifier
                            .size(35.dp)
                            .align(Alignment.Center),
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.width(15.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = mealName,
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Text(
                    text = recipeTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = calories,
                        fontSize = 14.sp,
                        color = Color.Black,
                    )

                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Colors.Primary)
                    ) {}

                    Text(
                        text = time,
                        fontSize = 14.sp,
                        color = Color.Black,
                    )
                }
            }

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_delete_custom),
                    contentDescription = "Deleting recipe icon",
                    tint = Colors.Delete,
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
    }
}


