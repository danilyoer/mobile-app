package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*
import androidx.compose.material.Text


@Composable
fun HabitCalendar(
    modifier: Modifier = Modifier,
    onDayClick: (LocalDate) -> Unit = {}
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    val days = remember(currentMonth) {
        val firstDayOfMonth = currentMonth.atDay(1)
        val lastDayOfMonth = currentMonth.atEndOfMonth()
        val daysInMonth = mutableListOf<LocalDate>()

        val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
        repeat(firstDayOfWeek) {
            daysInMonth.add(LocalDate.MIN)
        }

        var date = firstDayOfMonth
        while (!date.isAfter(lastDayOfMonth)) {
            daysInMonth.add(date)
            date = date.plusDays(1)
        }

        daysInMonth
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp)
    ) {
        CalendarHeader(
            currentMonth = currentMonth,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            DayOfWeek.values().forEach { dayOfWeek ->
                Text(
                    text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxSize(),
            content = {
                items(days) { day ->
                    CalendarDay(
                        day = day,
                        isSelected = day == selectedDate,
                        onClick = {
                            if (day != LocalDate.MIN) {
                                selectedDate = day
                                onDayClick(day)
                            }
                        }
                    )
                }
            }
        )
    }
}