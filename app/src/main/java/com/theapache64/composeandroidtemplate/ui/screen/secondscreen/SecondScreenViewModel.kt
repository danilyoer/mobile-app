package com.theapache64.composeandroidtemplate.ui.screen.secondscreen

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.serialization.json.Json
import com.theapache64.composeandroidtemplate.data.model.Habit
import java.io.File
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.theapache64.composeandroidtemplate.data.model.HabitItem


@HiltViewModel
class SecondScreenViewModel @Inject constructor() : ViewModel() {

    private val _habits = MutableStateFlow<List<HabitItem>>(emptyList())
    val habits = _habits.asStateFlow()


    fun loadHabits(context: Context) {
        val dir = File(context.filesDir, "habits")
        if (!dir.exists()) return

        val habitList = dir.listFiles()
            ?.filter { it.extension == "json" }
            ?.mapNotNull { file ->
                try {
                    val json = file.readText()
                    val habit = Json.decodeFromString<Habit>(json)
                    HabitItem(habit, file.name)
                } catch (e: Exception) {
                    null
                }
            }
            ?.sortedByDescending { it.habit.createdAt } // ← сортируем по дате
            ?: emptyList()


        _habits.value = habitList
    }
}
