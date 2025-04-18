package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val frequencies = listOf("Ежедневно", "Еженедельно", "Через день", "Раз в месяц")

@Composable
fun FrequencyDropdown(
    selectedFrequency: String,
    isDropdownExpanded: Boolean,
    onFrequencySelected: (String) -> Unit,
    onDropdownExpandedChange: (Boolean) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedFrequency,
            onValueChange = { },
            label = { Text("Частота") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onDropdownExpandedChange(true) },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        )
        DropdownMenu(
            expanded = isDropdownExpanded,
            onDismissRequest = { onDropdownExpandedChange(false) }
        ) {
            frequencies.forEach { frequency ->
                DropdownMenuItem(onClick = {
                    onFrequencySelected(frequency)
                    onDropdownExpandedChange(false)
                }) {
                    Text(text = frequency)
                }
            }
        }
    }
}