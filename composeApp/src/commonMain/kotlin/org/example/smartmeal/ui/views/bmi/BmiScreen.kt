package org.example.smartmeal.ui.views.bmi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.smartmeal.ui.components.health.CustomFormButton
import org.example.smartmeal.ui.views.bmi_form.BmiFormScreen
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.pic_bmi
import smartmeal_project.composeapp.generated.resources.pic_bmi_categories
import smartmeal_project.composeapp.generated.resources.pic_bmi_pattern


// Widok przedstawiający informację ogólne na temat BMI

class BmiScreen : Screen {
    @Composable
    override fun Content() {

        val viewModel = koinViewModel<BmiViewModel>()

        BmiContent(
            viewModel = viewModel,
            onNavigateToForm = {}
        )
    }
}


@Composable
fun BmiContent(
    viewModel: BmiViewModel,
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
            text = "Czym jest BMI?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Text(
            text = "BMI (Body Mass Index) - to prosty wskaźnik masy ciała, który pozwala ocenić czy nasza waga jest odpowiednia do wzrostu.", // Na razie nie będziemy formatować SemiBodla dla BMI sprawdszamy ułożenie elementów
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
        )

        Image(
            painter = painterResource(Res.drawable.pic_bmi), //
            contentDescription = "BMI diagram",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )


        Text(
            text = "Jest to najpopularniejsze narzędzie do wstępnego diagnozowania niedowagi, prawidłowej masy ciała czy też otyłości. Wylicza się je z następującego wzoru:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )


        Image(
            painter = painterResource(Res.drawable.pic_bmi_pattern), //
            contentDescription = "BMI formula",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Image(
            painter = painterResource(Res.drawable.pic_bmi_categories), //
            contentDescription = "BMI formula",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        CustomFormButton(
            text = "Oblicz BMI",
            onClick = onNavigateToForm,
            enabled = true,
        )
    }
}