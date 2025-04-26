package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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

@Composable
fun HabitCalendar(
    modifier: Modifier = Modifier,
    onDayClick: (LocalDate) -> Unit = {},
    completedDates: Set<LocalDate> = emptySet(),
    selectedDate: LocalDate? = null,
    isSelectable: Boolean = true,
    targetDates: Set<LocalDate> = emptySet()
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    // Определяем первый день месяца (начиная с понедельника)
    val firstDayOfMonth = currentMonth.atDay(1)
    val firstDayOfWeek = (firstDayOfMonth.dayOfWeek.value - 1 + 7) % 7  // Понедельник = 0, воскресенье = 6

    // Создаём список дней месяца
    val days = remember(currentMonth) {
        val lastDayOfMonth = currentMonth.atEndOfMonth()
        val daysInMonth = mutableListOf<LocalDate>()

        // Добавляем пустые ячейки перед первым днем месяца
        repeat(firstDayOfWeek) {
            daysInMonth.add(LocalDate.MIN)  // Заглушки для пустых ячеек
        }

        // Добавляем все дни месяца
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
            .height(400.dp)
            .padding(8.dp)
    ) {
        CalendarHeader(
            currentMonth = currentMonth,
            onPreviousMonth = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonth = { currentMonth = currentMonth.plusMonths(1) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Отображаем дни недели (понедельник, вторник и т.д.)
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

        // Отображаем дни месяца
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxSize(),
            content = {
                items(days) { day ->
                    CalendarDay(
                        day = day,
                        isSelected = day == selectedDate,
                        isCompleted = completedDates.contains(day),
                        isTarget = targetDates.contains(day),
                        onClick = { if (isSelectable) onDayClick(day) },
                        isSelectable = isSelectable
                    )
                }
            }
        )
    }
}

