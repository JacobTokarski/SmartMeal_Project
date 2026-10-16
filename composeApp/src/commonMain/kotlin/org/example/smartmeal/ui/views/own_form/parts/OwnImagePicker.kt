package org.example.smartmeal.ui.views.own_form.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.model.preset.PresetImages
import org.example.smartmeal.ui.theme.Colors
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_arrow

@Composable
fun OwnImagePicker(
    selectedUrl: String?,
    onSelect: (String) -> Unit,
    onClear: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clickable{ onBack() },
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow),
                    contentDescription = null,
                )

                Text(
                    text = "Cofnij",
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }

            Text(
                text = "Wybierz zdjęcie",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Bez zdjęcia",
                fontSize = 14.sp,
                color = Color.LightGray,
                modifier = Modifier
                    .clickable { onClear() }
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(15.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(PresetImages.all, key = { it.key }) { preset ->

                val isSelected = preset.url == selectedUrl

                Column(
                    modifier = Modifier.clickable {
                        onSelect(preset.url)
                    }
                ) {
                    Image(
                        painter = painterResource(preset.resource),
                        contentDescription = preset.label,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = if (isSelected) 3.dp else 1.dp,
                                color = if (isSelected) Colors.Primary else Color.LightGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                    )

                    Text(
                        preset.label,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(top = 4.dp)
                    )
                }
            }
        }
    }
}