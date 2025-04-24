package com.theapache64.composeandroidtemplate.ui.screen.habitdetail

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.theapache64.composeandroidtemplate.data.model.Habit
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.viewinterop.AndroidView
import android.widget.CalendarView

@Composable
fun HabitDetailScreen(fileName: String, onBack: () -> Unit = {}) {
    val context = LocalContext.current
    var habit by remember { mutableStateOf<Habit?>(null) }

    LaunchedEffect(fileName) {
        val dir = File(context.filesDir, "habits")
        val file = File(dir, fileName)
        if (file.exists()) {
            try {
                val json = file.readText()
                habit = Json.decodeFromString(json)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    habit?.let { h ->
        HabitDetailContent(habit = h, fileName = fileName, onBack = onBack)
    } ?: run {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Загрузка...")
        }
    }
}

@Composable
fun HabitDetailContent(habit: Habit, fileName: String, onBack: () -> Unit) {
    val context = LocalContext.current

    val dateFormatted = remember(habit.createdAt) {
        val format = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        format.format(Date(habit.createdAt))
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Верх
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = onBack) {
                Text("← Назад")
            }
            Text(
                text = habit.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.alignByBaseline()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Дата и напоминание
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dateFormatted)
            Text(
                text = if (habit.reminderEnabled) "Нужно напоминание" else "Не нужно напоминание"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Как часто? : ${habit.frequency}")
        Spacer(modifier = Modifier.height(8.dp))

        Text("Категория: ${habit.category}")
        Spacer(modifier = Modifier.height(8.dp))

        Text("Сколько дней выполнено: 0")
        Spacer(modifier = Modifier.height(24.dp))

        // Календарик
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            factory = { context ->
                CalendarView(context).apply {
                    setBackgroundColor(android.graphics.Color.WHITE)
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка удаления
        Button(
            onClick = {
                val dir = File(context.filesDir, "habits")
                val file = File(dir, fileName)
                if (file.exists()) {
                    file.delete()
                    onBack()
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)
        ) {
            Text("Удалить привычку", color = MaterialTheme.colors.onError)
        }
    }
}
