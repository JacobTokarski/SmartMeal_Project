package org.example.smartmeal.ui.views.tdee

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.ui.components.health.CustomFormButton
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.pic_tdee
import smartmeal_project.composeapp.generated.resources.pic_tdee_pattern


// Widok przedstawiający informację ogólne na temat TDEE

class TdeeScreen: Screen {
    @Composable
    override fun Content() {

        TdeeContent(
            onNavigateToForm = {}
        )
    }
}


@Composable
fun TdeeContent(
    onNavigateToForm: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 15.dp) //
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Czym jest TDEE?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Text(
            text = "TDEE (Total Daily Energy Expediture) to całkowite dzienne zapotrzebowanie kaloryczne. Określa ono dokładną liczbę kalorii, którą twój organizm spala każdego dnia.", // Na razie nie będziemy formatować SemiBodla dla BMI sprawdszamy ułożenie elementów
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
        )

        Image(
            painter = painterResource(Res.drawable.pic_tdee),
            contentDescription = "TDEE diagram",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = "Jego wartość obliczamy, stosując iloczyn podstawowej przemiany materii oraz tak zwanej wartości PAL, czyli współczynnika aktywności fizycznej.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )

        Image(
            painter = painterResource(Res.drawable.pic_tdee_pattern),
            contentDescription = "TDEE formula",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        CustomFormButton(
            text = "Oblicz TDEE",
            onClick = onNavigateToForm,
            enabled = true,
        )
    }
}