package org.example.smartmeal.ui.views.profile.parts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.components.profile.CustomSocialButtons
import org.example.smartmeal.ui.components.profile.CustomStatsCard
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_arrow
import smartmeal_project.composeapp.generated.resources.ic_facebook
import smartmeal_project.composeapp.generated.resources.ic_instagram
import smartmeal_project.composeapp.generated.resources.ic_stat_favorite
import smartmeal_project.composeapp.generated.resources.ic_stat_kitchen
import smartmeal_project.composeapp.generated.resources.ic_twitter

@Composable
fun ColumnScope.ProfileBody(
    favoriteCount: Int,
    ownRecipesCount: Int,
    onInstagramClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onTwitterClick: () -> Unit,
) {
    Text(
        text = "Statystyki",
        style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = Color.Black.copy(alpha = 0.7f),
        ),
        modifier = Modifier
            .align(Alignment.Start)
    )

    Spacer(modifier = Modifier.height(15.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CustomStatsCard(
            statsName = "Ulubione",
            statsNumber = favoriteCount.toString(),
            statsIcon = Res.drawable.ic_stat_favorite,
            modifier = Modifier.weight(1f)
        )

        CustomStatsCard(
            statsName = "Moja kuchnia",
            statsNumber = ownRecipesCount.toString(),
            statsIcon = Res.drawable.ic_stat_kitchen,
            modifier = Modifier.weight(1f)
        )
    }

    Spacer(modifier = Modifier.height(25.dp))

    Text(
        text = "Śledź nas po więcej inspiracji!",
        style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = Color.Black.copy(alpha = 0.7f),
        ),
        modifier = Modifier
            .align(Alignment.Start)
    )

    Spacer(modifier = Modifier.height(15.dp))

    CustomSocialButtons(
        text = "Instagram",
        onClick = onInstagramClick,
        enabled = true,
        leadingIcon = Res.drawable.ic_instagram,
        trailingIcon = Res.drawable.ic_arrow
    )

    Spacer(modifier = Modifier.height(20.dp))

    CustomSocialButtons(
        text = "Facebook",
        onClick = onFacebookClick,
        enabled = true,
        leadingIcon = Res.drawable.ic_facebook,
        trailingIcon = Res.drawable.ic_arrow
    )

    Spacer(modifier = Modifier.height(20.dp))

    CustomSocialButtons(
        text = "X",
        onClick = onTwitterClick,
        enabled = true,
        leadingIcon = Res.drawable.ic_twitter,
        trailingIcon = Res.drawable.ic_arrow
    )

    Spacer(modifier = Modifier.height(30.dp))
}