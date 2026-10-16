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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.smartmeal.data.repository.home.CustomRecipe
import org.example.smartmeal.ui.views.own_form.parts.OwnFormBody
import org.example.smartmeal.ui.views.own_form.parts.OwnFormFooter
import org.example.smartmeal.ui.views.own_form.parts.OwnFormHeader
import org.example.smartmeal.ui.views.own_form.parts.OwnImagePicker

@Composable
fun OwnFormScreen(
    initialRecipe: CustomRecipe? = null,
    onSaveClick: (CustomRecipe) -> Unit,
    onBackClick: () -> Unit,
) {

    val isEditing = initialRecipe != null
    var selectedImageUrl by remember(initialRecipe?.id) { mutableStateOf(initialRecipe?.imageUrl) }
    var showPicker by remember { mutableStateOf(false) }
    var showError by rememberSaveable { mutableStateOf(false) }

    var title by remember(initialRecipe?.id) { mutableStateOf(initialRecipe?.title.orEmpty()) }
    var time by remember(initialRecipe?.id) {
        mutableStateOf(
            initialRecipe?.time.orEmpty().filter { it.isDigit() })
    }
    var calories by remember(initialRecipe?.id) {
        mutableStateOf(
            initialRecipe?.calories.orEmpty().filter { it.isDigit() })
    }
    var category by remember(initialRecipe?.id) { mutableStateOf(initialRecipe?.category.orEmpty()) }
    var type by remember(initialRecipe?.id) { mutableStateOf(initialRecipe?.type.orEmpty()) }


    if (showPicker) {
        OwnImagePicker(
            selectedUrl = selectedImageUrl,
            onSelect = { selectedImageUrl = it; showPicker = false },
            onClear = { selectedImageUrl = null; showPicker = false },
            onBack = { showPicker = false }
        )
    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            OwnFormHeader(
                title = if (isEditing) "Edytuj przepis" else "Dodaj przepis",
            )

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
            ) {
                item {
                    OwnFormBody(
                        title = title,
                        onTitleChange = { title = it },
                        category = category,
                        onCategoryChange = { category = it },
                        type = type,
                        onTypeChange = { type = it },
                        time = time,
                        onTimeChange = { time = it },
                        calories = calories,
                        onCaloriesChange = { calories = it },
                        showError = showError
                    )
                }

                item {
                    OwnFormFooter(
                        onSaveClick = {
                            val isValid = title.isNotBlank() && category.isNotBlank() && type.isNotBlank()

                            if (isValid) {
                                showError = false
                                onSaveClick(
                                    CustomRecipe(
                                        id = initialRecipe?.id.orEmpty(),
                                        title = title.trim(),
                                        category = category.trim(),
                                        type = type.trim(),
                                        imageUrl = selectedImageUrl,
                                        calories = if (calories.isNotBlank()) "$calories kcal" else "",
                                        time = if (time.isNotBlank()) "$time min" else "",
                                        isFavorite = initialRecipe?.isFavorite ?: false,
                                    )
                                )
                            } else {
                                showError = true
                            }
                        },
                        onBackClick = onBackClick,
                        onAddImageClick = { showPicker = true },
                        selectedImageUrl = selectedImageUrl
                    )
                }
            }
        }
    }
}