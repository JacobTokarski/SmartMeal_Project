package org.example.smartmeal.ui.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.ui.components.CustomRecipeCard
import org.koin.compose.viewmodel.koinViewModel

class HomeScreen : Screen {
    @Composable
    override fun Content() {

        val viewModel = koinViewModel<HomeViewModel>()

        HomeContent(
            viewModel = viewModel
        )
    }
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel
) {

    val allRecipes = HomeDemoData.allRecipes
    val recommended = allRecipes.take(5)
    val breakfast = allRecipes.drop(5).take(5)
    val lunch = allRecipes.drop(10).take(5)
    val dinner = allRecipes.drop(15).take(5)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 10.dp, vertical = 15.dp),
        ) {
            // Wersja testowa -> Powitanie użytkownika

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "Cześć username 👋", //
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Text(
                    text = "Jaką potrawę dzisiaj wybierasz?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            RecipeSection(
                title = "Rekomendowane",
                recipes = recommended
            )

            RecipeSection(
                title = "Śniadanie",
                recipes = breakfast
            )

            RecipeSection(
                title = "Obiad",
                recipes = lunch
            )

            RecipeSection(
                title = "Kolacja",
                recipes = dinner
            )
        }
    }
}