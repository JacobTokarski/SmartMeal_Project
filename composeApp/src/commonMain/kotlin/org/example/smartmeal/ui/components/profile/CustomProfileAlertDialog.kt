package org.example.smartmeal.ui.components.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.smartmeal.ui.theme.Colors

// Komponent ten zawiera okno dialogowe, które użytkownik otrzymuje podczas wylogowania się z aplikacji (aktualny stan -> Part 1)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomProfileAlertDialog(
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        confirmButton = {

            Button(
                onClick = onConfirmClick,
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.Primary,
                    contentColor = Colors.Primary
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                ),

            ) {
                Text(
                    text = "Wyloguj",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }
        },

        dismissButton = {

            Button(
                onClick = onDismissClick,
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.LightGray
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                )
            ) {
                Text(
                    text = "Anuluj",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        },

        title = {

            Text(
                text = "Czy na pewno chcesz się wylogować?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },

        text = {

            Text(
                text = "Jeśli zatwierdzisz tą operację zostaniesz wylogowany z systemu",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        },
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .border(BorderStroke(1.dp, Colors.Primary)),
        shape = RoundedCornerShape(15.dp),
        containerColor = Color.White,
    )
}