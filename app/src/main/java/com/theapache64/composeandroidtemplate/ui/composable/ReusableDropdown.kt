package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun ReusableDropdown(
    label: String,
    options: List<String>,
    selectedValue: String,
    expanded: Boolean,
    onValueSelected: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {
    // Измеряем высоту TextField для смещения меню
    var anchorHeightPx by remember { mutableStateOf(0) }
    val density = LocalDensity.current
    val anchorHeightDp = with(density) { anchorHeightPx.toDp() }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coords ->
                    anchorHeightPx = coords.size.height
                },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        )

        // Прозрачный слой поверх TextField для клика по всему полю
        Box(
            modifier = Modifier
                .matchParentSize()
                .testTag("${label}Dropdown")        // <-- тег для открытия меню
                .clickable { onExpandedChange(true) }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            offset = DpOffset(x = 0.dp, y = -anchorHeightDp)
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    onClick = {
                        onValueSelected(option)
                        onExpandedChange(false)
                    },
                    modifier = Modifier.testTag("${label}Option")  // <-- общий тег для всех опций
                ) {
                    Text(text = option)
                }
            }
        }
    }
}
