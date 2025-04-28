package com.theapache64.composeandroidtemplate.ui.screen.habitdetail

import java.time.Instant
import java.time.ZoneId

import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.theapache64.composeandroidtemplate.data.model.Habit
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.Alignment
import com.theapache64.composeandroidtemplate.ui.composable.HabitCalendar
import java.time.LocalDate

@Composable
fun HabitDetailScreen(fileName: String, onBack: () -> Unit = {}) {
    val context = LocalContext.current
    val habitState = remember { mutableStateOf<Habit?>(null) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }

    LaunchedEffect(fileName) {
        val dir = File(context.filesDir, "habits")
        val file = File(dir, fileName)
        if (file.exists()) {
            try {
                val json = file.readText()
                habitState.value = Json.decodeFromString(Habit.serializer(), json)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    habitState.value?.let {
        HabitDetailContent(habitState = habitState, selectedDate = selectedDate, fileName = fileName, onBack = onBack)
    } ?: run {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Загрузка...")
        }
    }
}

@Composable
fun HabitDetailContent(
    habitState: MutableState<Habit?>,
    selectedDate: MutableState<LocalDate?>,
    fileName: String,
    onBack: () -> Unit
) {
    val habit = habitState.value
    val context = LocalContext.current
    val today = LocalDate.now()

    val targetDates = remember(habit) {
        habit?.let { calculateTargetDates(it) } ?: emptySet()
    }

    val dateFormatted = remember(habit?.createdAt) {
        habit?.createdAt?.let {
            val format = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            format.format(Date(it))
        } ?: ""
    }

    LaunchedEffect(Unit) {
        if (selectedDate.value == null) {
            selectedDate.value = today
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = onBack) {
                Text("← Назад")
            }
            habit?.let {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.alignByBaseline()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dateFormatted)
            Text(
                text = if (habit?.reminderEnabled == true) "Нужно напоминание" else "Не нужно напоминание"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        habit?.let {
            Text("Как часто? : ${it.frequency}")
            Spacer(modifier = Modifier.height(8.dp))

            Text("Категория: ${it.category}")
            Spacer(modifier = Modifier.height(8.dp))

            Text("Сколько дней выполнено: ${it.completedDates.size}")
            Spacer(modifier = Modifier.height(24.dp))

            HabitCalendar(
                completedDates = habit.completedDates.map { LocalDate.parse(it) }.toSet(),
                targetDates = targetDates,
                selectedDate = today,
                isSelectable = true,
                onDayClick = { clickedDate ->
                    if (clickedDate == today) {
                        habitState.value?.let { currentHabit ->
                            val formattedDate = clickedDate.toString()
                            val updatedCompletedDates = if (currentHabit.completedDates.contains(formattedDate)) {
                                currentHabit.completedDates - formattedDate
                            } else {
                                currentHabit.completedDates + formattedDate
                            }
                            val updatedHabit = currentHabit.copy(completedDates = updatedCompletedDates)
                            habitState.value = updatedHabit

                            // Сохраняем обновление в файл
                            val dir = File(context.filesDir, "habits")
                            val file = File(dir, fileName)
                            if (file.exists()) {
                                file.writeText(Json.encodeToString(Habit.serializer(), updatedHabit))
                            }
                        }
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val dir = File(context.filesDir, "habits")
                val file = File(dir, fileName)
                if (file.exists()) {
                    file.delete()
                    onBack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)
        ) {
            Text("Удалить привычку", color = MaterialTheme.colors.onError)
        }
    }
}





private fun calculateTargetDates(habit: Habit): Set<LocalDate> {
    val startDate = Instant.ofEpochMilli(habit.createdAt)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val stepDays = when (habit.frequency) {
        "Ежедневно" -> 1
        "Через день" -> 2
        "Еженедельно" -> 7
        "Раз в месяц" -> 30
        else -> 1
    }

    val count = habit.time.toIntOrNull() ?: 0

    return (0 until count).map { offset ->
        // Переход от первого дня без добавления лишнего дня
        startDate.plusDays(stepDays.toLong() * offset)
    }.toSet()
}

