package com.theapache64.composeandroidtemplate.ui.screen.habitdetail

import androidx.compose.runtime.Composable
import androidx.compose.material.Text


@Composable
fun HabitDetailScreen(fileName: String) {
    Text("Экран привычки: $fileName")
}
