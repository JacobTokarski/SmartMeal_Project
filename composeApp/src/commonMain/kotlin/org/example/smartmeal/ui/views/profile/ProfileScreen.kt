package org.example.smartmeal.ui.views.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.ui.components.profile.CustomProfileAlertDialog
import org.example.smartmeal.ui.views.login.LoginScreen
import org.example.smartmeal.ui.views.profile.parts.ProfileBody
import org.example.smartmeal.ui.views.profile.parts.ProfileFooter
import org.example.smartmeal.ui.views.profile.parts.ProfileHeader
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.pic_background

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ProfileViewModel>()

        ProfileContent(
            viewModel = viewModel
        )
    }
}

@Composable
fun ProfileContent(
    viewModel: ProfileViewModel
) {

    var showAppBar by remember { mutableStateOf(false) }
    val url = LocalUriHandler.current
    val tabNavigator = LocalNavigator.currentOrThrow
    val state by viewModel.uiState.collectAsState()
    val logout by viewModel.logoutEvent.collectAsState()


    LaunchedEffect(logout) {
        if (logout) {
            val rootNavigator = tabNavigator.parent ?: tabNavigator
            rootNavigator.replaceAll(LoginScreen)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(Res.drawable.pic_background),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.8f),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ProfileHeader(
                displayName = state.displayName,
                avatarName = state.avatarName
            )

            ProfileBody(
                favoriteCount = state.favoriteCount,
                ownRecipesCount = state.ownRecipesCount,
                onInstagramClick = { url.openUri("https://www.instagram.com") },
                onFacebookClick = { url.openUri("https://www.facebook.com") },
                onTwitterClick = { url.openUri("https://www.x.com") }
            )

            ProfileFooter(
                onLogoutClick = { showAppBar = true }
            )

            if (showAppBar) {
                CustomProfileAlertDialog(
                    onConfirmClick = {
                        showAppBar = false
                        viewModel.onLogoutClick()
                    },
                    onDismissClick = {
                        showAppBar = false
                    }
                )
            }
        }
    }
}