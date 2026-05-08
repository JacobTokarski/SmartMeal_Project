package org.example.smartmeal.ui.views.own

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.ui.components.CustomSearchField
import org.example.smartmeal.ui.views.own.parts.OwnEmptyState
import org.example.smartmeal.ui.views.own.parts.OwnFooter
import org.example.smartmeal.ui.views.own.parts.OwnHeader
import org.koin.compose.viewmodel.koinViewModel
import smartmeal_project.composeapp.generated.resources.Res

object OwnScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<OwnViewModel>()

        OwnContent(
            viewModel = viewModel
        )
    }
}

// - Na razie logika wstępna

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
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {

            OwnHeader(
                query = state.searchQuery,
                onQueryChange = { viewModel.onSearchQuery(it)}
            )

            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (state.recipes.isEmpty()) {
                    OwnEmptyState()
                } else {

                    // Logika LazyColumn w przyszłość i kreowanie formularza
                    Text(
                        text = "W przyszłości formularz!"
                    )
                }
            }

            OwnFooter(
                onAddClick = { viewModel.onAddRecipe()}
            )
        }
    }
}