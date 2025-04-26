package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TimeField(time: String, onValueChange: (String) -> Unit, maxDays: Int) {
    OutlinedTextField(
        value = time,
        onValueChange = onValueChange,
        label = { Text("Время выполнения") },
        placeholder = { Text("От 1 до $maxDays") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}
