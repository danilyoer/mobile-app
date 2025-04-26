package com.theapache64.composeandroidtemplate.ui.screen.secondscreen
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import com.theapache64.composeandroidtemplate.ui.screen.Screen
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theapache64.composeandroidtemplate.data.model.Habit
import androidx.compose.foundation.lazy.items



@Composable
fun SecondScreen(
    navController: NavController,
    onBackToSplash: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: SecondScreenViewModel = hiltViewModel()
    val habitList by viewModel.habits.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHabits(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Кнопка "Назад"
        Button(
            onClick = onBackToSplash,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        ) {
            Text("Назад")
        }

        // Заголовок
        Text(
            text = "Сохранённые привычки",
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(
                items = habitList, // список
                key = { it.fileName } // ключ
            ) { habitItem ->
                HabitCard(
                    habit = habitItem.habit,
                    onClick = {
                        navController.navigate(Screen.HabitDetail.createRoute(habitItem.fileName))
                    }
                )
            }
        }

    }
}


@Composable
fun HabitCard(habit: Habit, onClick: () -> Unit) {
    val completedDays = habit.completedDates.size
    val totalDays = habit.time.toIntOrNull() ?: 0

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Название: ${habit.name}")
                Text("Категория: ${habit.category}")
                Text("Частота: ${habit.frequency}")
                Text("Напоминания: ${if (habit.reminderEnabled) "Да" else "Нет"}")
            }

            if (totalDays > 0) {
                Text(
                    text = "$completedDays / $totalDays",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


