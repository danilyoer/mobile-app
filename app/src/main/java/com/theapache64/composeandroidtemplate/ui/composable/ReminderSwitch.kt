package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Spacer

@Composable
fun ReminderSwitch(isReminderEnabled: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Включить напоминания")
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = isReminderEnabled,
            onCheckedChange = onCheckedChange
        )
    }
}