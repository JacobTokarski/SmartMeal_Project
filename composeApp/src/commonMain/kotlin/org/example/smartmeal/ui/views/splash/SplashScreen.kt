package org.example.smartmeal.ui.views.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import org.example.smartmeal.ui.views.login.LoginScreen
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.pic_leaf
import smartmeal_project.composeapp.generated.resources.pic_logo
import smartmeal_project.composeapp.generated.resources.pic_range

object SplashScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            delay(2500)

            navigator.replace(LoginScreen)
        }
        SplashContent()
    }
}

@Composable
fun SplashContent() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(Res.drawable.pic_leaf),
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .height(280.dp)
                        .align(Alignment.TopStart)
                        .offset(((-10).dp), y = ((-10).dp))
                )


                Image(
                    painter = painterResource(Res.drawable.pic_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .height(300.dp)
                        .width(300.dp)
                        .align(Alignment.Center)
                )

                Image(
                    painter = painterResource(Res.drawable.pic_range),
                    contentDescription = null,
                    modifier = Modifier
                        .width(128.dp)
                        .height(134.dp)
                        .align(Alignment.BottomEnd)
                        .navigationBarsPadding()
                        .offset(x = 10.dp, y = 0.dp)
                )
            }
        }
    }
}
