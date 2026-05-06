package org.example.smartmeal.ui.navigation.tabs
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
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
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.example.smartmeal.ui.theme.Colors
import org.example.smartmeal.ui.utils.RecipesSubTab
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_recipes_bottom

object HomeTab : Tab {
    @Composable
    override fun Content() {

        var selectedTab by remember { mutableStateOf(RecipesSubTab.Main) }

        Column(
            modifier = Modifier.fillMaxSize().background(Color.White)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            SecondaryScrollableTabRow(
                selectedTabIndex = selectedTab.ordinal,
                containerColor = Color.White,
                edgePadding = 0.dp,
                indicator = {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(selectedTab.ordinal)
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        color = Colors.Primary,
                        height = 2.dp
                    )
                },
                divider = {}
            ) {
                RecipesSubTab.entries.forEach { tab ->

                    val isSelected = selectedTab == tab

                    Tab(
                        selected = isSelected,
                        onClick = { selectedTab = tab },
                        text = {
                            Text(
                                text = tab.title,
                                modifier = Modifier
                                    .padding(bottom = 4.dp),
                                color = if (isSelected) Colors.Primary else Colors.Not_Selected,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                fontSize = 15.sp
                            )
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Selected view: ${selectedTab.title}", // Testowo aby zobaczyć czy przełączanie działa
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }
    }

    override val options: TabOptions
        @Composable get() {
            val title = "Przepisy"
            val icon = painterResource(Res.drawable.ic_recipes_bottom)

            return remember {
                TabOptions(
                    index = 3u,
                    title = title,
                    icon = icon
                )
            }
        }
}