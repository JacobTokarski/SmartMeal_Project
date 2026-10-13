package org.example.smartmeal.ui.views.own_form.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.smartmeal.model.health.HealthFormError
import org.example.smartmeal.ui.components.health.CustomFormBmiFields

@Composable
fun OwnFormBody(
    title: String,
    onTitleChange: (String) -> Unit,
    category: String,
    onCategoryChange: (String) -> Unit,
    type: String,
    onTypeChange: (String) -> Unit,
    time: String,
    onTimeChange: (String) -> Unit,
    calories: String,
    onCaloriesChange: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        CustomFormBmiFields(
            value = title,
            onValueChange = onTitleChange,
            label = "Nazwa potrawy",
            placeholder = "Wprowadź nazwę potrawy*",
            maxLength = 35,
            error = HealthFormError.None
        )

        CustomFormBmiFields(
            value = category,
            onValueChange = onCategoryChange,
            label = "Kategoria potrawy",
            placeholder = "Wprowadź kategorię potrawy (np. obiad)*",
            maxLength = 25,
            error = HealthFormError.None
        )


        CustomFormBmiFields(
            value = type,
            onValueChange = onTypeChange,
            label = "Typ kuchni",
            placeholder = "Wprowadź typ kuchni (np. azjatycka)*",
            maxLength = 25,
            error = HealthFormError.None
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CustomFormBmiFields(
                value = time,
                onValueChange = onTimeChange,
                label = "Czas",
                placeholder = "np. 30",
                unit = "min",
                maxLength = 5,
                modifier = Modifier.weight(1f),
                error = HealthFormError.None
            )

            CustomFormBmiFields(
                value = calories,
                onValueChange = onCaloriesChange,
                label = "Kalorie",
                placeholder = "np. 450",
                unit = "kcal",
                maxLength = 5,
                modifier = Modifier.weight(1f),
                error = HealthFormError.None
            )
        }
    }
}