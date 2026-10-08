package org.example.smartmeal.ui.components

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
import coil3.compose.AsyncImage
import org.example.smartmeal.model.home.RecipeCardAction
import org.example.smartmeal.ui.theme.Colors
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_checked
import smartmeal_project.composeapp.generated.resources.ic_clock
import smartmeal_project.composeapp.generated.resources.ic_delete
import smartmeal_project.composeapp.generated.resources.ic_edit
import smartmeal_project.composeapp.generated.resources.ic_heart
import smartmeal_project.composeapp.generated.resources.pic_camera
@Composable
fun CustomRecipeCard(
    title: String,
    category: String,
    type: String,
    action: RecipeCardAction,
    hasImage: Boolean = false,
    imageUrl: String? = null,
    calories: String = "1500 kcal",
    time: String = "35 minut",
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(168.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Colors.Icon_NotSelected) //
            .border(1.dp, Color.White.copy(alpha = 0.7f), RoundedCornerShape(15.dp))
            .clickable { onClick() }
            .then(
                when {
                    action is RecipeCardAction.Selection && action.isSelected -> Modifier
                        .border(2.dp, Colors.Primary, RoundedCornerShape(15.dp))

                    !hasImage -> Modifier
                        .border(1.dp, Colors.Primary, RoundedCornerShape(15.dp))

                    else -> Modifier
                }
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.85f)
        ) {
            if (hasImage && imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
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

            if (action is RecipeCardAction.Selection && action.isSelected) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Colors.Primary.copy(alpha = 0.2f))
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White.copy(alpha = 0.7f))
                    .border(1.dp, Color.White.copy(alpha = 0.7f), RoundedCornerShape(15.dp))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = type,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black, //
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                when (action) {

                    is RecipeCardAction.Selection -> {
                        if (action.isSelected) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(Colors.Primary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_checked),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                        }
                    }

                    is RecipeCardAction.EditDelete -> {

                        val iconBackground =
                            if (hasImage) Color.White.copy(alpha = 0.5f) else Color.Transparent

                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(iconBackground)
                                .clickable { action.onEditClick() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_edit),
                                contentDescription = "Ikona edycji",
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(iconBackground)
                                .clickable { action.onDeleteClick() },
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

                    is RecipeCardAction.Favorite -> {
                        val iconBackground =
                            if (hasImage) Color.White.copy(alpha = 0.5f) else Color.Transparent

                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(iconBackground)
                                .clickable { action.onToggle() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(
                                    if (action.isFavorite) Res.drawable.ic_edit // Ikona będzie do poprawy
                                    else Res.drawable.ic_heart
                                ),
                                contentDescription = "Favorite Home Icon",
                                modifier = Modifier.size(20.dp),
                                tint = Colors.Icon_Color
                            )
                        }
                    }
                }
            }

            if (hasImage) {

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.White.copy(alpha = 0.7f))
                        .border(1.dp, Color.White.copy(alpha = 0.7f), RoundedCornerShape(15.dp))
                        .padding(horizontal = 6.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Icon(
                        painter = painterResource(Res.drawable.ic_clock), // Na razie taka ikona
                        contentDescription = "Ikona zegara",
                        modifier = Modifier
                            .size(10.dp),
                        tint = Color(0xFF4A3324)

                    )

                    Text(
                        text = calories,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )

                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Colors.Primary)
                    ) {}

                    Text(
                        text = time,
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
                        .background(Color.Black.copy(alpha = 0.85f)) // Do zmiany w przypadku braku zdjęcia będzie na pewno tło profilu
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_edit),
                        contentDescription = "Ikona zegara",
                        modifier = Modifier
                            .size(10.dp), //
                        tint = Color(0xFF4A3324) //

                    )

                    Text(
                        text = calories,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )

                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.Black)
                    ) {}

                    Text(
                        text = time,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(3.dp)) // Wartość może ulec zmianie

            Text(
                text = category,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                color = Color.Black
            )
        }
    }
}
