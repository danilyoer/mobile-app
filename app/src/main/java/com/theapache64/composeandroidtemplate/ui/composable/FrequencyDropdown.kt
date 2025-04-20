package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.runtime.Composable

@Composable
fun FrequencyDropdown(
    selectedFrequency: String,
    isDropdownExpanded: Boolean,
    onFrequencySelected: (String) -> Unit,
    onDropdownExpandedChange: (Boolean) -> Unit
) {
    val frequencies = listOf("Ежедневно", "Еженедельно", "Через день", "Раз в месяц")

    ReusableDropdown(
        label = "Частота",
        options = frequencies,
        selectedValue = selectedFrequency,
        expanded = isDropdownExpanded,
        onValueSelected = onFrequencySelected,
        onExpandedChange = onDropdownExpandedChange
    )
}
