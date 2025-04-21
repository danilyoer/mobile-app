package com.theapache64.composeandroidtemplate.ui.screen.secondscreen
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theapache64.composeandroidtemplate.data.model.Habit
import com.theapache64.composeandroidtemplate.ui.screen.Screen


@Composable
fun SecondScreen(navController: NavController, onBackToSplash: () -> Unit) {
    val context = LocalContext.current
    val viewModel: SecondScreenViewModel = hiltViewModel()
    val habitList by viewModel.habits.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHabits(context)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {

        Button(
            onClick = onBackToSplash,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        ) {
            Text("Назад")
        }

        Text("Сохранённые привычки", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(habitList) { habit ->
                HabitCard(habit = habit, onClick = {
                    val fileName = "habit_${habit.createdAt}.json"
                    navController.navigate(Screen.HabitDetail.createRoute(fileName))
                })

            }
        }
    }
}

@Composable
fun HabitCard(habit: Habit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Название: ${habit.name}")
            Text("Категория: ${habit.category}")
            Text("Частота: ${habit.frequency}")
            Text("Напоминания: ${if (habit.reminderEnabled) "Да" else "Нет"}")
        }
    }
}

