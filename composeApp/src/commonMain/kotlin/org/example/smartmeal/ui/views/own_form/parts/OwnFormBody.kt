package org.example.smartmeal.ui.views.own_form.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.components.CustomEmailField
import org.example.smartmeal.ui.components.CustomFormField
import org.example.smartmeal.ui.theme.Colors

@Composable

fun OwnFormBody(
    title: String,
    onTitleChange: (String) -> Unit,
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
        CustomEmailField(
            value = title,
            onValueChange = onTitleChange,
            placeholder = "Nazwa potrawy *",
        )


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Czas przygotowania: ",
                fontSize = 15.sp,
                color = Colors.Text_Form.copy(alpha = 0.5f),
            )

            Spacer(modifier = Modifier.width(5.dp))

            CustomFormField(
                value = time,
                onValueChange = onTimeChange,
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "min",
                fontSize = 15.sp,
                color = Colors.Text_Form.copy(alpha = 0.5f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Ilość kalorii: ",
                fontSize = 15.sp,
                color = Colors.Text_Form.copy(alpha = 0.5f)

            )

            Spacer(modifier = Modifier.width(5.dp))

            CustomFormField(
                value = calories,
                onValueChange = onCaloriesChange,
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp),
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "kcal",
                fontSize = 15.sp,
                color = Colors.Text_Form.copy(alpha = 0.5f)
            )
        }
    }
}