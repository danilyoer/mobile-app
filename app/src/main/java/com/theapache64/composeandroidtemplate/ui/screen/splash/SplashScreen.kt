package com.theapache64.composeandroidtemplate.ui.screen.splash

import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theapache64.composeandroidtemplate.R
import com.theapache64.composeandroidtemplate.data.model.Habit
import com.theapache64.composeandroidtemplate.data.model.HabitItem
import kotlinx.serialization.decodeFromString
import androidx.compose.ui.platform.testTag
import kotlinx.serialization.json.Json
import java.io.File
import java.time.LocalDate

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onSplashFinished: () -> Unit,
    onGoToSecond: () -> Unit,
    onGoToCreateHabit: () -> Unit
) {
    val isSplashFinished by viewModel.isSplashFinished.collectAsState(initial = false)
    val context = LocalContext.current
    var todayHabits by remember { mutableStateOf<List<HabitItem>>(emptyList()) }

    LaunchedEffect(isSplashFinished) {
        if (isSplashFinished) {
            onSplashFinished()
        }
    }

    LaunchedEffect(Unit) {
        todayHabits = loadTodayHabits(context)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .testTag("SplashScreenBox")) {

        // Версия приложения
        val versionName by viewModel.versionName.collectAsState()
        Text(
            text = versionName,
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 8.dp, top = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))



            // Логотип
            Image(
                painter = painterResource(id = R.drawable.ic_compose_logo),
                contentDescription = stringResource(id = R.string.cd_app_logo),
                modifier = Modifier
                    .size(220.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.label_hello_world),
                style = MaterialTheme.typography.h3,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = onGoToCreateHabit,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                modifier = Modifier                 // <<< добавь цепочку
                    .fillMaxWidth()
                    .testTag("CreateHabitButton")
            ) {
                Text("Создать привычку")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onGoToSecond,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Мои привычки")
            }

            Spacer(modifier = Modifier.weight(1f)) // Расширяем до 1/3 экрана вниз
        }

        // Блок списка привычек
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f) // 40% от экрана занимает список
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text(
                text = "Привычки на сегодня",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (todayHabits.isEmpty()) {
                Text(text = "Нет привычек на сегодня")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(todayHabits, key = { it.fileName }) { habitItem ->
                        TodayHabitRow(habitItem = habitItem, onToggle = { updatedItem ->
                            todayHabits = todayHabits.map {
                                if (it.fileName == updatedItem.fileName) updatedItem else it
                            }
                        })
                    }
                }
            }
        }


    }
}


@Composable
fun TodayHabitRow(habitItem: HabitItem, onToggle: (HabitItem) -> Unit) {
    val context = LocalContext.current
    val today = LocalDate.now()
    val isCompletedToday = habitItem.habit.completedDates.contains(today.toString())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                toggleHabitToday(context, habitItem, onToggle)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = habitItem.habit.name,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Canvas(
            modifier = Modifier
                .size(24.dp)
        ) {
            drawCircle(
                color = if (isCompletedToday) Color(0xFF4CAF50) else Color.LightGray,
                radius = size.minDimension / 2,
                center = Offset(size.width / 2, size.height / 2)
            )
        }
    }
}

private fun toggleHabitToday(context: Context, habitItem: HabitItem, onToggle: (HabitItem) -> Unit) {
    val today = LocalDate.now()
    val formattedToday = today.toString()

    val updatedCompletedDates = if (habitItem.habit.completedDates.contains(formattedToday)) {
        habitItem.habit.completedDates - formattedToday
    } else {
        habitItem.habit.completedDates + formattedToday
    }

    val updatedHabit = habitItem.habit.copy(completedDates = updatedCompletedDates)
    val updatedItem = HabitItem(updatedHabit, habitItem.fileName)

    // Сохраняем изменения в файл
    val dir = File(context.filesDir, "habits")
    val file = File(dir, habitItem.fileName)
    if (file.exists()) {
        file.writeText(Json.encodeToString(Habit.serializer(), updatedHabit))
    }

    onToggle(updatedItem)
}

private fun loadTodayHabits(context: Context): List<HabitItem> {
    val habitsDir = File(context.filesDir, "habits")
    if (!habitsDir.exists()) return emptyList()

    val today = LocalDate.now()

    return habitsDir.listFiles()
        ?.filter { it.extension == "json" }
        ?.mapNotNull { file ->
            try {
                val json = file.readText()
                val habit = Json.decodeFromString<Habit>(json)
                val targetDates = calculateTargetDates(habit)
                if (today in targetDates) {
                    HabitItem(habit, file.name)
                } else null
            } catch (e: Exception) {
                null
            }
        }
        ?.sortedBy { it.habit.name }
        ?: emptyList()
}

private fun calculateTargetDates(habit: Habit): Set<LocalDate> {
    val startDate = java.time.Instant.ofEpochMilli(habit.createdAt)
        .atZone(java.time.ZoneId.systemDefault())
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
        startDate.plusDays(stepDays.toLong() * offset)
    }.toSet()
}
