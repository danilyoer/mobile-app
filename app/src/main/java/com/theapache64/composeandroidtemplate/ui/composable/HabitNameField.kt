package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HabitNameField(habitName: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = habitName,
        onValueChange = { newText ->
            if (newText.length <= 35) {
                onValueChange(newText)
            }
        },
        label = { Text("Название привычки") },
        placeholder = { Text("Название от 3 до 35 символов") },
        modifier = Modifier.fillMaxWidth()
    )
}