package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.runtime.Composable

@Composable
fun CategoryDropdown(
    selectedCategory: String,
    isDropdownExpanded: Boolean,
    onCategorySelected: (String) -> Unit,
    onDropdownExpandedChange: (Boolean) -> Unit
) {
    val categories = listOf("Здоровье", "Продуктивность", "Спорт", "Обучение", "Другое")

    ReusableDropdown(
        label = "Категория",
        options = categories,
        selectedValue = selectedCategory,
        expanded = isDropdownExpanded,
        onValueSelected = onCategorySelected,
        onExpandedChange = onDropdownExpandedChange
    )
}
