package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackButtonRow(onBack: () -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Назад",
            modifier = Modifier
                .clickable { onBack() }
                .padding(8.dp),
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Новая привычка",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}