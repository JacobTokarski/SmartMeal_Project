package org.example.smartmeal.ui.views.tdee_form.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.model.health.Gender
import org.example.smartmeal.model.health.ActivityLevel
import org.example.smartmeal.model.health.HealthFormError
import org.example.smartmeal.ui.components.health.ActivityLevelSelector
import org.example.smartmeal.ui.components.health.CustomFormBmiFields
import org.example.smartmeal.ui.components.health.CustomFormSelectorButton
import org.example.smartmeal.ui.theme.Colors

@Composable
fun TdeeFormBody(
    age: String,
    onAgeChange: (String) -> Unit,
    height: String,
    onHeightChange: (String) -> Unit,
    weight: String,
    onWeightChange: (String) -> Unit,
    selectedGender: Gender?,
    onGenderSelect: (Gender) -> Unit,
    selectedActivityLevel: ActivityLevel?,
    onActivityLevelSelect: (ActivityLevel) -> Unit,
    heightError: HealthFormError,
    ageError: HealthFormError,
    weightError: HealthFormError,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = "Płeć*",
            modifier = Modifier
                .align(Alignment.Start),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Colors.Primary
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CustomFormSelectorButton(
                text = "Mężczyzna",
                isSelected = selectedGender == Gender.MALE,
                onClick = { onGenderSelect(Gender.MALE) },
                modifier = Modifier
                    .weight(1f)
            )

            CustomFormSelectorButton(
                text = "Kobieta",
                isSelected = selectedGender == Gender.FEMALE,
                onClick = { onGenderSelect(Gender.FEMALE) },
                modifier = Modifier
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Wiek*",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Colors.Primary,
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                text = "Wzrost*",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Colors.Primary,
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                text = "Waga*",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Colors.Primary,
                modifier = Modifier
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CustomFormBmiFields(
                value = age,
                onValueChange = onAgeChange,
                placeholder = "32",
                unit = "lata",
                error = ageError,
                modifier = Modifier
                    .weight(1f)
            )

            CustomFormBmiFields(
                value = height,
                onValueChange = onHeightChange,
                placeholder = "178",
                unit = "cm",
                error = heightError,
                modifier = Modifier
                    .weight(1f)
            )

            CustomFormBmiFields(
                value = weight,
                onValueChange = onWeightChange,
                placeholder = "76",
                unit = "kg",
                error = weightError,
                modifier = Modifier
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Poziom aktywności*",
            modifier = Modifier
                .align(Alignment.Start),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Colors.Primary
        )

        Spacer(modifier = Modifier.height(10.dp))

        ActivityLevelSelector(
            selected = selectedActivityLevel,
            onSelect = onActivityLevelSelect
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}