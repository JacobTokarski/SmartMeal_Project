package org.example.smartmeal.ui.views.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.model.home.RecipeCardAction
import org.example.smartmeal.ui.components.CustomRecipeCard
import org.example.smartmeal.ui.views.favorite.parts.FavoriteEmptyState
import org.example.smartmeal.ui.views.favorite.parts.FavoriteHeader
import org.koin.compose.viewmodel.koinViewModel

object FavoriteScreen : Screen {
    @Composable
    override fun Content() {

        val viewModel = koinViewModel<FavoriteViewModel>()

        FavoriteContent(
            viewModel = viewModel
        )
    }
}


@Composable
fun FavoriteContent(
    viewModel: FavoriteViewModel
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
            FavoriteHeader(
                query = state.searchQuery,
                onQueryChange = { viewModel.onSearchQuery(it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                when {

                    state.recipes.isEmpty() -> {
                        FavoriteEmptyState()
                    }

                    else -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(top = 10.dp, bottom = 80.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
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
                                    action = RecipeCardAction.Favorite(
                                        isFavorite = true,
                                        onToggle = { viewModel.onToggleFavorite(recipe.id)}
                                    ),
                                    hasImage = recipe.hasImage,
                                    imageUrl = recipe.imageUrl,
                                    calories = recipe.calories,
                                    time = recipe.time,
                                    onClick = {}
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}