package com.theapache64.composeandroidtemplate.ui.screen.createhabit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.theapache64.composeandroidtemplate.ui.composable.*

@Composable
fun CreateHabitScreen(onNavigateBackToMain: () -> Unit) {

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
        BackButtonRow(onBack = onNavigateBackToMain)
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
        ActionButtons( onCancel = onNavigateBackToMain, onSave = { /* потом */ })
    }
}


// Списки опций для выпадающих списков
private val frequencies = listOf("Ежедневно", "Еженедельно", "Через день", "Раз в месяц")
private val categories = listOf("Здоровье", "Продуктивность", "Спорт", "Обучение", "Другое")