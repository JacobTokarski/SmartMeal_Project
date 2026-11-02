package org.example.smartmeal.ui.views.tdee_form.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors
import org.jetbrains.compose.resources.painterResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.ic_delete
import smartmeal_project.composeapp.generated.resources.ic_delete_custom

@Composable
fun TdeeFormHeader(
    onDeleteClick: (() -> Unit)? = null,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .statusBarsPadding(),
        color = Colors.Form_Header_Background,
        shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {

            Text(
                text = "TDEE",
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            if (onDeleteClick != null) {
                IconButton(
                    onClick = onDeleteClick,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_delete_custom),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.CenterEnd),
                    )
                }
            }
        }
    }
    
    Spacer(modifier = Modifier.height(15.dp))
}