package org.example.smartmeal.ui.views.selection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.datetime.LocalDate
import org.example.smartmeal.ui.components.CustomRecipeCard
import org.example.smartmeal.ui.components.CustomSearchField
import org.example.smartmeal.ui.theme.Colors
import org.example.smartmeal.ui.utils.RecipeSubTabSelection
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_search

class SelectionScreen(
    private val mealName: String,
    private val selectedDate: LocalDate
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<SelectionViewModel> {
            parametersOf(mealName, selectedDate)
        }

        SelectionContent(
            viewModel = viewModel,
            onBackNavigate = { navigator.pop() }
        )
    }
}

@Composable
fun SelectionContent(
    viewModel: SelectionViewModel,
    onBackNavigate: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(RecipeSubTabSelection.Own) }

    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    viewModel.confirmSelection()
                    onBackNavigate()
                }, //
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .height(50.dp)
                    .background(Color.White),
                enabled = viewModel.selectedRecipeId != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.Primary,
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Zatwierdź wybór",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 15.dp, horizontal = 20.dp)
                .background(Color.White),
        ) {

            SecondaryScrollableTabRow(
                selectedTabIndex = selectedTab.ordinal,
                containerColor = Color.White,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 15.dp),
                indicator = {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(selectedTab.ordinal)
                            .padding(horizontal = 20.dp, vertical = 15.dp),
                        color = Colors.Primary,
                        height = 2.dp
                    )
                },
                divider = {}
            ) {
                RecipeSubTabSelection.entries.forEach { tab ->

                    val isSelected = selectedTab == tab

                    Tab(
                        selected = isSelected,
                        onClick = { selectedTab = tab },
                        text = {
                            Text(
                                text = tab.title,
                                modifier = Modifier
                                    .padding(5.dp),
                                color = if (isSelected) Colors.Primary else Colors.NotSelected,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                fontSize = 15.sp
                            )
                        }
                    )
                }
            }

            CustomSearchField(
                value = viewModel.searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(it) },
                placeholder = "Wyszukaj przepis",
                leadingIcon = Res.drawable.ic_search
            )

            Spacer(modifier = Modifier.height(15.dp))

            val recipesToShow = when (selectedTab) {
                RecipeSubTabSelection.Own -> viewModel.ownRecipes
                RecipeSubTabSelection.Favorites -> viewModel.favoriteRecipes
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(recipesToShow) { recipe ->
                    CustomRecipeCard(
                        title = recipe.title,
                        hasImage = recipe.hasImage,
                        calories = recipe.calories,
                        time = recipe.time,
                        isSelectionMode = true,
                        isSelected = viewModel.selectedRecipeId == recipe.id,
                        onClick = { viewModel.toggleRecipeSelection(recipe.id)},
                    )
                }
            }
        }
    }
}