package org.example.smartmeal.model.selection

import org.example.smartmeal.data.repository.home.CustomRecipe
import org.example.smartmeal.model.recipe.Recipe

enum class RecipeSource { OWN, CATALOG }

data class RecipeReference(val id: String, val source: RecipeSource)

data class SelectableRecipe(
    val id: String,
    val title: String,
    val category: String,
    val type: String,
    val hasImage: Boolean,
    val imageUrl: String?,
    val calories: String,
    val time: String,
    val source: RecipeSource
) {
    val reference: RecipeReference get() = RecipeReference(id, source)
}

fun CustomRecipe.toSelectable() = SelectableRecipe(
    id = id,
    title = title,
    category = category,
    type = type,
    hasImage = hasImage,
    imageUrl = null,
    calories = calories,
    time = time,
    source = RecipeSource.OWN
)

fun Recipe.toSelectable() = SelectableRecipe(
    id = id,
    title = title,
    category = category,
    type = type,
    hasImage = imageUrl != null,
    imageUrl = imageUrl,
    calories = calories,
    time = time,
    source = RecipeSource.CATALOG
)
