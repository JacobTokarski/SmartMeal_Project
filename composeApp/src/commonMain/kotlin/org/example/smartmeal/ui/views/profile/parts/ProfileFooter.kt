package org.example.smartmeal.ui.views.profile.parts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.smartmeal.ui.components.profile.CustomLogoutButton
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_arrow
import smartmeal_project.composeapp.generated.resources.ic_logout

@Composable
fun ProfileFooter(
    onLogoutClick: () -> Unit
) {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 0.5.dp,
        color = Color.Gray
    )

    Spacer(modifier = Modifier.height(20.dp))

    CustomLogoutButton(
        text = "Wyloguj się",
        onClick = onLogoutClick,
        enabled = true,
        leadingIcon = Res.drawable.ic_logout,
        trailingIcon = Res.drawable.ic_arrow
    )
}