package org.example.smartmeal.ui.views.favorite.parts

import androidx.compose.runtime.Composable
import org.example.smartmeal.ui.components.CustomSearchField
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_search

@Composable
fun FavoriteHeader(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    CustomSearchField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = "Wyszukaj przepis:",
        leadingIcon = Res.drawable.ic_search
    )
}