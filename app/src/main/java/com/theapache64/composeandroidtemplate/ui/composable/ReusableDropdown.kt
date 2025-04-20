package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ReusableDropdown(
    label: String,
    options: List<String>,
    selectedValue: String,
    expanded: Boolean,
    onValueSelected: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { onExpandedChange(true) }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onValueSelected(option)
                    onExpandedChange(false)
                }) {
                    Text(text = option)
                }
            }
        }
    }
}
