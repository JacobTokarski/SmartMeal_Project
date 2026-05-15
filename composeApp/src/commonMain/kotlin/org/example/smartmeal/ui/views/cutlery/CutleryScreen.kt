package org.example.smartmeal.ui.views.cutlery

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.ui.views.login.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel


object CutleryScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CutleryViewModel>()

        CutleryContent(
            viewModel = viewModel
        )
    }
}





@Composable
fun CutleryContent(
    viewModel: CutleryViewModel,

) {

}