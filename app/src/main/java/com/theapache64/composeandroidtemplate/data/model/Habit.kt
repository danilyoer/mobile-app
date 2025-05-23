package com.theapache64.composeandroidtemplate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Habit(
    val name: String,
    val frequency: String,
    val category: String,
    val time: String,
    val reminderEnabled: Boolean,
    val createdAt: Long,
    val completedDates: List<String> = emptyList() // новое поле для выполненных дат
)
