package com.theapache64.composeandroidtemplate.ui.screen.createhabit
import androidx.compose.ui.platform.LocalContext
import com.theapache64.composeandroidtemplate.data.model.Habit
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.theapache64.composeandroidtemplate.ui.composable.*

@Composable
fun CreateHabitScreen(onNavigateBackToMain: () -> Unit,
                      onNavigateToSecondScreen: () -> Unit)
 {
    val context = LocalContext.current
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

        BackButtonRow(onBack = onNavigateBackToMain)
        Spacer(modifier = Modifier.height(32.dp))

        HabitNameField(habitName) { habitName = it }
        Spacer(modifier = Modifier.height(16.dp))

        FrequencyDropdown(
            selectedFrequency,
            isFrequencyDropdownExpanded,
            onFrequencySelected = { selectedFrequency = it },
            onDropdownExpandedChange = { isFrequencyDropdownExpanded = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TimeField(time) { time = it }
        Spacer(modifier = Modifier.height(16.dp))

        ReminderSwitch(isReminderEnabled) { isReminderEnabled = it }
        Spacer(modifier = Modifier.height(16.dp))

        CategoryDropdown(
            selectedCategory,
            isCategoryDropdownExpanded,
            onCategorySelected = { selectedCategory = it },
            onDropdownExpandedChange = { isCategoryDropdownExpanded = it }
        )
        Spacer(modifier = Modifier.weight(1f))

        ActionButtons(
            onCancel = onNavigateBackToMain,
            onSave = {
                val habit = Habit(
                    name = habitName,
                    frequency = selectedFrequency,
                    category = selectedCategory,
                    time = time,
                    reminderEnabled = isReminderEnabled,
                    createdAt = System.currentTimeMillis()
                )

                val json = Json.encodeToString(habit)
                val dir = File(context.filesDir, "habits")
                dir.mkdirs()

                val fileName = "habit_${System.currentTimeMillis()}.json"
                val file = File(dir, fileName)
                file.writeText(json)

                onNavigateToSecondScreen()
            }
        )

    }
}


// Списки опций для выпадающих списков
private val frequencies = listOf("Ежедневно", "Еженедельно", "Через день", "Раз в месяц")
private val categories = listOf("Здоровье", "Продуктивность", "Спорт", "Обучение", "Другое")