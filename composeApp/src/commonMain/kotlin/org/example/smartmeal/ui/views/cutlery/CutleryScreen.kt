package org.example.smartmeal.ui.views.cutlery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import org.example.smartmeal.data.repository.DietPlanRepository
import org.example.smartmeal.data.repository.RecipeRepository
import org.example.smartmeal.ui.components.CustomCutleryCard
import org.example.smartmeal.ui.components.CustomFilledRecipeCard
import org.example.smartmeal.ui.theme.Colors
import org.example.smartmeal.ui.views.selection.SelectionScreen
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_calendar


class CutleryScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CutleryViewModel>()

        CutleryContent(
            viewModel = viewModel
        )
    }
}

@Composable
fun CutleryContent(
    viewModel: CutleryViewModel,
) {
    val calendarDays = remember { viewModel.getCalendarDays() }
    var showDataPicker by remember { mutableStateOf(false) }
    val navigator = LocalNavigator.currentOrThrow

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(
                        text = viewModel.selectedDate.month.name.lowercase()
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "${viewModel.selectedDate.year}",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }

                IconButton(
                    onClick = { showDataPicker = true },
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Colors.Icon_Back)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_calendar),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(calendarDays) { day ->

                    val isSelected = day.date == viewModel.selectedDate

                    Column(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Colors.Icon_Selected else Colors.Icon_NotSelected)
                            .clickable {
                                viewModel.selectedDate = day.date
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = day.dayName,
                            color = if (isSelected) Color.White else Color.Black, //
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 10.sp
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = day.dayNumber,
                            color = if (isSelected) Color.White else Color.Black, //
                            fontSize = 12.sp,
                            lineHeight = 10.sp
                        )

                        if (day.isToday) {

                            Spacer(modifier = Modifier.height(4.dp))

                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) Color.White else Colors.Primary)
                            ) {}
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 24.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Twój plan dnia:",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 12.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "${viewModel.selectedDate.dayOfMonth}",
                                style = MaterialTheme.typography.titleMedium,
                                color = Colors.Primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(vertical = 12.dp)
                            )

                            Spacer(modifier = Modifier.width(3.dp))

                            Text(
                                text = viewModel.selectedDate.month.name.lowercase()
                                    .replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.titleMedium,
                                color = Colors.Primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(vertical = 12.dp)
                            )

                            Spacer(modifier = Modifier.width(3.dp))

                            Text(
                                text = "${viewModel.selectedDate.year}",
                                style = MaterialTheme.typography.titleMedium,
                                color = Colors.Primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(vertical = 12.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    items(
                        items = viewModel.mealCategories,
                        key = { it }
                    ) { category ->

                        val assignedRecipeId =
                            DietPlanRepository.getRecipeId(viewModel.selectedDate, category)

                        val selectedRecipe = assignedRecipeId?.let { id ->
                            RecipeRepository.userRecipes.find { it.id == id }
                        }

                        if (selectedRecipe != null) {
                            CustomFilledRecipeCard(
                                mealName = category,
                                recipeTitle = selectedRecipe.title,
                                calories = selectedRecipe.calories,
                                time = selectedRecipe.time,
                                hasImage = selectedRecipe.hasImage,
                                onDeleteClick = {
                                    DietPlanRepository.removeRecipe(
                                        viewModel.selectedDate,
                                        category
                                    )
                                }
                            )
                        } else {
                            CustomCutleryCard(
                                recipeName = category,
                                onAddClick = {
                                    val rootNavigator = navigator.parent ?: navigator
                                    rootNavigator.push(SelectionScreen(mealName = category, selectedDate = viewModel.selectedDate))
                                },
                                onDeleteClick = {} //
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

            if (showDataPicker) {
                val dataPickerState = rememberDatePickerState(
                    initialSelectedDateMillis = viewModel.selectedDate
                        .atStartOfDayIn(TimeZone.currentSystemDefault())
                        .toEpochMilliseconds()
                )

                DatePickerDialog(
                    onDismissRequest = { showDataPicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                dataPickerState.selectedDateMillis?.let { millis ->
                                    viewModel.selectedDate = LocalDate
                                        .fromEpochDays((millis / 86400000).toInt())
                                }
                                showDataPicker = false
                            }) {
                            Text("Accept", color = Colors.Primary)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showDataPicker = false
                            }) {
                            Text("Cancel") //
                        }
                    }) {

                    DatePicker(state = dataPickerState)
                }
            }
        }
    }
}