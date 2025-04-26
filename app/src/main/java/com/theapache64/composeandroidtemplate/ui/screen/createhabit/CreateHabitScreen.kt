package com.theapache64.composeandroidtemplate.ui.screen.createhabit
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.theapache64.composeandroidtemplate.ui.composable.*
import com.theapache64.composeandroidtemplate.data.model.Habit
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import com.theapache64.composeandroidtemplate.R

@Composable
fun CreateHabitScreen(
    onNavigateBackToMain: () -> Unit,
    onNavigateToSecondScreen: () -> Unit
) {

    val context = LocalContext.current
    var habitName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(categories.first()) }
    var time by remember { mutableStateOf("") }
    var isReminderEnabled by remember { mutableStateOf(true) }
    var isFrequencyDropdownExpanded by remember { mutableStateOf(false) }
    var isCategoryDropdownExpanded by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val frequencies = listOf(
        stringResource(id = R.string.daily),
        stringResource(id = R.string.every_other_day),
        stringResource(id = R.string.weekly),
        stringResource(id = R.string.monthly)
    )
    var selectedFrequency by remember { mutableStateOf(frequencies.first()) }

    val maxDays = getMaxDaysForFrequency(selectedFrequency)
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
            selectedFrequency = selectedFrequency,
            isDropdownExpanded = isFrequencyDropdownExpanded,
            onFrequencySelected = { selectedFrequency = it },
            onDropdownExpandedChange = { isFrequencyDropdownExpanded = it },
            options = frequencies // <-- передаём список!
        )

        Spacer(modifier = Modifier.height(16.dp))
        TimeField(
            time = time,
            onValueChange = { newTime ->
                if (newTime.isEmpty() || newTime.toIntOrNull()?.let { it in 1..maxDays } == true) {
                    time = newTime
                }
            },
            maxDays = maxDays
        )

        Spacer(modifier = Modifier.height(16.dp))
        ReminderSwitch(isReminderEnabled) { isReminderEnabled = it }
        Spacer(modifier = Modifier.height(16.dp))
        CategoryDropdown(
            selectedCategory,
            isDropdownExpanded = isCategoryDropdownExpanded,
            onCategorySelected = { selectedCategory = it },
            onDropdownExpandedChange = { isCategoryDropdownExpanded = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        ActionButtons(
            onCancel = onNavigateBackToMain,
            onSave = {
                val timeValue = time.toIntOrNull()
                val maxDays = getMaxDaysForFrequency(selectedFrequency)

                when {
                    habitName.length < 3 || habitName.length > 35 -> {
                        errorMessage = "Название привычки должно содержать от 3 до 35 символов"
                        showErrorDialog = true
                    }
                    timeValue == null || timeValue !in 1..maxDays -> {
                        errorMessage = "Время выполнения должно быть числом от 1 до $maxDays"
                        showErrorDialog = true
                    }
                    else -> {
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
                }
            }
        )

    }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            confirmButton = {
                Button(onClick = { showErrorDialog = false }) {
                    Text("ОК")
                }
            },
            title = { Text("Ошибка") },
            text = { Text(errorMessage) }
        )
    }
}
private fun getMaxDaysForFrequency(frequency: String): Int {
    return when (frequency) {
        "Ежедневно" -> 365
        "Через день" -> 180
        "Еженедельно" -> 54
        "Раз в месяц" -> 12
        else -> 365
    }
}



// Списки для выпадающих списков
private val categories = listOf("Здоровье", "Продуктивность", "Спорт", "Обучение", "Другое")