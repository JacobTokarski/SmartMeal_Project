package org.example.smartmeal.ui.views.own

import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.model.home.RecipeCardAction
import org.example.smartmeal.ui.components.CustomRecipeCard
import org.example.smartmeal.ui.theme.Colors
import org.example.smartmeal.ui.views.own.parts.OwnEmptyState
import org.example.smartmeal.ui.views.own.parts.OwnFooter
import org.example.smartmeal.ui.views.own.parts.OwnHeader
import org.example.smartmeal.ui.views.own_form.OwnFormScreen
import org.koin.compose.viewmodel.koinViewModel

object OwnScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<OwnViewModel>()

        OwnContent(
            viewModel = viewModel
        )
    }
}

@Composable
fun OwnContent(
    viewModel: OwnViewModel,
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
                .padding(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 5.dp)
        ) {

            OwnHeader(
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

                    state.isFormVisible -> {
                        Dialog(
                            onDismissRequest = viewModel::onCloseForm,
                            properties = DialogProperties(
                                usePlatformDefaultWidth = false,
                                dismissOnBackPress = true
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White)
                            ) {
                                OwnFormScreen(
                                    initialRecipe = state.editRecipe,
                                    onSaveClick = viewModel::onSaveRecipe,
                                    onBackClick = viewModel::onCloseForm
                                )
                            }
                        }
                    }

                    state.recipes.isEmpty() -> {
                        OwnEmptyState()
                    }

                    else -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(top = 10.dp, bottom = 80.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.recipes) { currentRecipe ->
                                CustomRecipeCard(
                                    title = currentRecipe.title,
                                    category = currentRecipe.category,
                                    type = currentRecipe.type,
                                    hasImage = currentRecipe.hasImage,
                                    imageUrl = currentRecipe.imageUrl,
                                    calories = currentRecipe.calories,
                                    time = currentRecipe.time,
                                    onClick = {},
                                    action = RecipeCardAction.EditDelete(
                                        onEditClick = { viewModel.onEditClick(currentRecipe) },
                                        onDeleteClick = { viewModel.onDeleteClick(currentRecipe) }
                                    ),
                                )
                            }
                        }
                    }
                }
            }

            OwnFooter(onAddClick = viewModel::onAddClick)
        }
    }

    state.recipePendingDelete?.let { recipe ->

        AlertDialog(
            onDismissRequest = viewModel::onDismissDelete,
            title = {
                Text(
                    text = "Usunąć przepis?",
                )
            },

            text = {
                Text(
                    text = "Przepis „${recipe.title}” zostanie trwale usunięty, także z jadłospisu."
                )
            },

            confirmButton = {
                TextButton(onClick = viewModel::onConfirmDelete)
                {
                    Text(
                        text = "Usuń",
                        color = Colors.Delete
                    )
                }
            },

            dismissButton = {
                TextButton(onClick = viewModel::onDismissDelete) {
                    Text(
                        text = "Anuluj",
                        color = Color.Black
                    )
                }
            },
            containerColor = Color.White,
        )
    }
}