package org.example.smartmeal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import org.example.smartmeal.model.preset.PresetImages
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomRecipeImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {

    val preset = PresetImages.fromUrl(imageUrl)

    if (preset != null) {
        Image(
            painter = painterResource(preset.resource),
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
        )
    } else {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}