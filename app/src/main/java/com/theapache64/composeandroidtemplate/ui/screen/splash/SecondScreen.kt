package com.theapache64.composeandroidtemplate.ui.screen.splash

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SecondScreen() {
    Text("Привет со второго экрана!")
}

object SecondScreenDestination {
    const val route = "second_screen"
}
