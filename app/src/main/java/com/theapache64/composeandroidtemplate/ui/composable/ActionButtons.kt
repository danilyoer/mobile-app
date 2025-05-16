package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.testTag

@Composable
fun ActionButtons(
    onCancel: () -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onSave,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)

        ) {
            Text(text = "Сохранить", color = MaterialTheme.colors.onPrimary)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onCancel,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)),
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .testTag("CancelHabitScreenBox")
        ) {
            Text(text = "Отмена", color = MaterialTheme.colors.onSurface)
        }
    }
}
