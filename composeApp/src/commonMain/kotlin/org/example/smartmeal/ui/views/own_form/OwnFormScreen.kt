package org.example.smartmeal.ui.views.own_form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.smartmeal.ui.utils.Recipe
import org.example.smartmeal.ui.views.own_form.parts.OwnFormBody
import org.example.smartmeal.ui.views.own_form.parts.OwnFormFooter
import org.example.smartmeal.ui.views.own_form.parts.OwnFormHeader

@Composable
fun OwnFormScreen(
    onSaveClick: (Recipe) -> Unit,
    onBackClick: () -> Unit,
) {

    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OwnFormHeader(
            onSaveClick = {
                val newRecipe = Recipe(
                    title = title,
                    time = if (time.isNotEmpty()) "$time min" else "",
                    calories = if (calories.isNotEmpty()) "$calories kcal" else "",
                    hasImage = true
                )
                onSaveClick(newRecipe)
            },
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {

            item {
                OwnFormBody(
                    title = title,
                    onTitleChange = { title = it },
                    time = time,
                    onTimeChange = { time = it },
                    calories = calories,
                    onCaloriesChange = { calories = it }
                )
            }

            item {
                OwnFormFooter()
            }
        }
    }
}