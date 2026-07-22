package org.example.smartmeal.ui.views.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.ui.components.profile.CustomLogoutButton
import org.example.smartmeal.ui.components.profile.CustomProfileAlertDialog
import org.example.smartmeal.ui.components.profile.CustomSocialButtons
import org.example.smartmeal.ui.components.profile.CustomStatsCard
import org.example.smartmeal.ui.views.login.LoginScreen
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_arrow
import smartmeal_project.composeapp.generated.resources.ic_facebook
import smartmeal_project.composeapp.generated.resources.ic_instagram
import smartmeal_project.composeapp.generated.resources.ic_logout
import smartmeal_project.composeapp.generated.resources.ic_stat_favorite
import smartmeal_project.composeapp.generated.resources.ic_stat_kitchen
import smartmeal_project.composeapp.generated.resources.ic_stat_ai
import smartmeal_project.composeapp.generated.resources.ic_twitter
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
                painter = painterResource(Res.drawable.pic_background), // zdjęcie to ulegnie zmianie
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .size(120.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.secondaryContainer,
                shadowElevation = 8.dp,
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "J",
                        style = MaterialTheme.typography.displayLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Witaj ponownie Jakub!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Statystyki",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomStatsCard(
                    statsName = "Ulubione",
                    statsNumber = "14",
                    statsIcon = Res.drawable.ic_stat_favorite
                )

                CustomStatsCard(
                    statsName = "Moja kuchnia",
                    statsNumber = "7",
                    statsIcon = Res.drawable.ic_stat_kitchen
                )

                CustomStatsCard(
                    statsName = "Wsparcie AI",
                    statsNumber = "10",
                    statsIcon = Res.drawable.ic_stat_ai
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Śledź nas po więcej inspiracji",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomSocialButtons(
                text = "Instagram",
                onClick = { url.openUri("https://www.instagram.com")},
                enabled = true,
                leadingIcon = Res.drawable.ic_instagram,
                trailingIcon = Res.drawable.ic_arrow
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomSocialButtons(
                text = "Facebook",
                onClick = { url.openUri("https://www.facebook.com")},
                enabled = true,
                leadingIcon = Res.drawable.ic_facebook,
                trailingIcon = Res.drawable.ic_arrow
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomSocialButtons(
                text = "X",
                onClick = { url.openUri("https://www.twitter.com")},
                enabled = true,
                leadingIcon = Res.drawable.ic_twitter,
                trailingIcon = Res.drawable.ic_arrow
            )

            Spacer(modifier = Modifier.height(60.dp))

            CustomLogoutButton(
                text = "Wyloguj się",
                onClick = { showAppBar = true },
                enabled = true,
                leadingIcon = Res.drawable.ic_logout,
                trailingIcon = Res.drawable.ic_arrow
            )

            if (showAppBar) {
                CustomProfileAlertDialog(
                    onConfirmClick = {
                        showAppBar = false

                        val rootNavigator = tabNavigator.parent ?: tabNavigator

                        rootNavigator.replaceAll(LoginScreen)
                    },
                    onDismissClick = {
                        showAppBar = false
                    }
                )
            }
        }
    }
}