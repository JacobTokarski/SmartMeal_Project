package org.example.smartmeal.ui.views.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.model.home.RecipeCardAction
import org.example.smartmeal.ui.components.CustomRecipeCard
import org.example.smartmeal.ui.views.search.parts.SearchHeader
import org.koin.compose.viewmodel.koinViewModel

object SearchScreen: Screen {
    @Composable
    override fun Content() {

        val viewModel = koinViewModel<SearchViewModel>()

        SearchContent(
            viewModel = viewModel
        )
    }
}


@Composable
fun SearchContent(
    viewModel: SearchViewModel
) {

    val state by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            SearchHeader(
                query = state.searchQuery,
                onQueryChange = viewModel::onSearchQuery
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (state.recipes.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (state.searchQuery.isBlank()) "Ładowanie przepisów..." else "Nie znaleziono przepisów",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = state.recipes,
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
                            action = RecipeCardAction.Favorite(
                                isFavorite = recipe.isFavorite,
                                onToggle = { viewModel.onToggleFavorite(recipe.id) }
                            ),
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}