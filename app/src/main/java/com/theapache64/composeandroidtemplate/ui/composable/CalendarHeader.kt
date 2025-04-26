package com.theapache64.composeandroidtemplate.ui.composable


import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun CalendarHeader(
    currentMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onPreviousMonth) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Предыдущий месяц")
        }
        Text(
            text = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + currentMonth.year,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onBackground
        )
        IconButton(onClick = onNextMonth) {
            Icon(Icons.Filled.ArrowForward, contentDescription = "Следующий месяц")
        }
    }
}
