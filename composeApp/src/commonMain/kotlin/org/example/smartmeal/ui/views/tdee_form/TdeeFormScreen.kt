package org.example.smartmeal.ui.views.tdee_form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.ui.theme.Colors
import org.example.smartmeal.ui.utils.health.Gender
import org.example.smartmeal.ui.utils.health.calculatePAL
import org.example.smartmeal.ui.views.tdee_form.parts.TdeeFormBody
import org.example.smartmeal.ui.views.tdee_form.parts.TdeeFormFooter
import org.example.smartmeal.ui.views.tdee_form.parts.TdeeFormHeader

// Plik, który będzie zawierał formularz umożliwiający wyliczenie TDEE

class TdeeFormScreen: Screen {
    @Composable
    override fun Content() {
        TdeeContent(
            onCancelClick = {},
            onSaveClick = {}
        )
    }
}


@Composable
fun TdeeContent(
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
) {

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf<Gender?>(null)}
    var selectedActivityLevel by remember { mutableStateOf<calculatePAL?>(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TdeeFormHeader()

        TdeeFormBody(
            age = age,
            onAgeChange = { age = it },
            height = height,
            onHeightChange = { height = it },
            weight = weight,
            onWeightChange = { weight = it },
            selectedGender = selectedGender,
            onGenderSelect = { selectedGender = it},
            selectedActivityLevel = selectedActivityLevel,
            onActivityLevelSelect = { selectedActivityLevel = it},
        )

        TdeeFormFooter(
            onCancelClick = onCancelClick,
            onSaveClick = onSaveClick
        )
    }
}