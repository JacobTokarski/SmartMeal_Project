package org.example.smartmeal.ui.views.own.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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

@Composable
fun OwnEmptyState() {
    val composition = rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/lottie/empty_own_recipe.json").decodeToString()
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
                .size(170.dp)

        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Brak własnych przepisów",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,

        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Kliknij przycisk +, aby stworzyć swój pierwszy przepis.",
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = Color.Black,
        )
    }
}