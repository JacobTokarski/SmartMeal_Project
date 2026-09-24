package org.example.smartmeal.ui.views.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.data.repository.FavoriteRepository
import org.example.smartmeal.model.home.RecipeCardAction
import org.example.smartmeal.model.recipe.Recipe
import org.example.smartmeal.ui.components.CustomRecipeCard

// Dodany widok kompozycyjny wyświetlający kategorie (na razie tylko do testów)

@Composable
fun RecipeSection(
    title: String,
    recipes: List<Recipe>,
    favoriteIds: Set<String>
) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black
    )

    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        recipes.forEach { recipe ->
            CustomRecipeCard(
                title = recipe.title,
                category = recipe.category,
                type = recipe.type,
                hasImage = recipe.hasImage,
                calories = recipe.calories,
                time = recipe.time,
                onClick = {},
                action = RecipeCardAction.Favorite(
                    isFavorite = recipe.id in favoriteIds,
                    onToggle = { FavoriteRepository.toggle(recipe.id)}
                )
            )
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
}