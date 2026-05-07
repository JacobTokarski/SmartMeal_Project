package org.example.smartmeal.ui.views.own

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.viewmodel.koinViewModel

object OwnScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<OwnViewModel>()

        //

        OwnContent(
            viewModel = viewModel
        )
    }
}


@Composable
fun OwnContent(
    viewModel: OwnViewModel,
) {
    // UI naszego widoku
}