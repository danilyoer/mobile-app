package com.theapache64.composeandroidtemplate.ui.screen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Dashboard : Screen("dashboard")
    object Second : Screen("second")
    object CreateHabit : Screen("create_habit")
    object HabitDetail : Screen("habit_detail/{fileName}") {
        fun createRoute(fileName: String): String = "habit_detail/$fileName"
    }

}