package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val categories = listOf("Здоровье", "Продуктивность", "Спорт", "Обучение", "Другое")

@Composable
fun CategoryDropdown(
    selectedCategory: String,
    isDropdownExpanded: Boolean,
    onCategorySelected: (String) -> Unit,
    onDropdownExpandedChange: (Boolean) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedCategory,
            onValueChange = { },
            label = { Text("Категория") },
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
            categories.forEach { category ->
                DropdownMenuItem(onClick = {
                    onCategorySelected(category)
                    onDropdownExpandedChange(false)
                }) {
                    Text(text = category)
                }
            }
        }
    }
}