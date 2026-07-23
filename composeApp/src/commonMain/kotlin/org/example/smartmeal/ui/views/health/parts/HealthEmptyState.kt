package org.example.smartmeal.ui.views.health.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import smartmeal_project.composeapp.generated.resources.Res

// Widok odpowiedzialny za przechowywanie pustego stanu (bez komponentów TDEE/BMI) ekranu Health (animacja Lottie + tekst)

@Composable
fun HealthEmptyState() {

    val composition = rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/lottie/empty_health_status.json").decodeToString()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            rememberLottiePainter(
                composition = composition.value,
                iterations = Int.MAX_VALUE
            ),
            contentDescription = "Empty state animation",
            modifier = Modifier
                .size(200.dp)
        )

        Text(
            text = "Brak obliczonych wskaźników",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Oblicz swoje wskaźniki zdrowotne wybierając je z menu powyżej",
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}