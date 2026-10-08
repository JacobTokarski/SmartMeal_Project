package org.example.smartmeal.ui.views.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.model.home.RecipeCardAction
import org.example.smartmeal.model.recipe.Recipe
import org.example.smartmeal.ui.components.CustomRecipeCard

@Composable
fun RecipeSection(
    title: String,
    recipes: List<Recipe>,
    favoriteIds: Set<String>,
    onToggleFavorite: (String) -> Unit
) {

    if (recipes.isNotEmpty()) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = recipes,
                key = { recipe -> recipe.id }
            ) { recipe ->
                CustomRecipeCard(
                    title = recipe.title,
                    category = recipe.category,
                    type = recipe.type,
                    hasImage = recipe.hasImage,
                    imageUrl = recipe.imageUrl,
                    calories = recipe.calories,
                    time = recipe.time,
                    onClick = {},
                    action = RecipeCardAction.Favorite(
                        isFavorite = recipe.id in favoriteIds,
                        onToggle = { onToggleFavorite(recipe.id) }
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}