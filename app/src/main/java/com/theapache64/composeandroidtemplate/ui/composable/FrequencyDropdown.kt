package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.runtime.Composable

@Composable
fun FrequencyDropdown(
    selectedFrequency: String,
    isDropdownExpanded: Boolean,
    onFrequencySelected: (String) -> Unit,
    onDropdownExpandedChange: (Boolean) -> Unit,
    options: List<String>
) {
    ReusableDropdown(
        label = "Частота",
        options = options, // <-- используем переданный список
        selectedValue = selectedFrequency,
        expanded = isDropdownExpanded,
        onValueSelected = onFrequencySelected,
        onExpandedChange = onDropdownExpandedChange
    )
}
