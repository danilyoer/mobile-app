package com.theapache64.composeandroidtemplate.ui.screen.createhabit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.theapache64.composeandroidtemplate.ui.composable.*

@Composable
fun CreateHabitScreen() {
    // Состояния для полей ввода
    var habitName by remember { mutableStateOf("") }
    var selectedFrequency by remember { mutableStateOf(frequencies.first()) }
    var selectedCategory by remember { mutableStateOf(categories.first()) }
    var time by remember { mutableStateOf("") }
    var isReminderEnabled by remember { mutableStateOf(true) }

    var isFrequencyDropdownExpanded by remember { mutableStateOf(false) }
    var isCategoryDropdownExpanded by remember { mutableStateOf(false) } // Состояния для отображения выпадающих списков

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Верхняя панель с кнопкой "Назад" и заголовком
        BackButtonRow()
        Spacer(modifier = Modifier.height(32.dp))

        // Поле "Название привычки"
        HabitNameField(habitName) { habitName = it }
        Spacer(modifier = Modifier.height(16.dp))

        // Выпадающий список "Частота"
        FrequencyDropdown(
            selectedFrequency,
            isFrequencyDropdownExpanded,
            onFrequencySelected = { selectedFrequency = it },
            onDropdownExpandedChange = { isFrequencyDropdownExpanded = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле "Время выполнения"
        TimeField(time) { time = it }
        Spacer(modifier = Modifier.height(16.dp))

        // Переключатель "Включить напоминания"
        ReminderSwitch(isReminderEnabled) { isReminderEnabled = it }
        Spacer(modifier = Modifier.height(16.dp))

        // Выпадающий список "Категория"
        CategoryDropdown(
            selectedCategory,
            isCategoryDropdownExpanded,
            onCategorySelected = { selectedCategory = it },
            onDropdownExpandedChange = { isCategoryDropdownExpanded = it }
        )
        Spacer(modifier = Modifier.weight(1f))

        // Кнопки "Сохранить" и "Отмена"
        ActionButtons()
    }
}

@Composable
fun ActionButtons() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { /* Обработчик сохранения */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Сохранить", color = MaterialTheme.colors.onPrimary)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Обработчик отмены */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)),
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        ) {
            Text(text = "Отмена", color = MaterialTheme.colors.onSurface)
        }
    }
}

// Списки опций для выпадающих списков
private val frequencies = listOf("Ежедневно", "Еженедельно", "Через день", "Раз в месяц")
private val categories = listOf("Здоровье", "Продуктивность", "Спорт", "Обучение", "Другое")