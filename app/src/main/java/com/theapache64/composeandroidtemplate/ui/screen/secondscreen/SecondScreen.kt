package com.theapache64.composeandroidtemplate.ui.screen.secondscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreen(
    onBackToSplash: () -> Unit // ← добавили коллбек
) {
    Box(modifier = Modifier.fillMaxSize()) {

        // Кнопка "Назад"
        Button(
            onClick = onBackToSplash,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Text("Назад")
        }

        // Центрированный текст
        Text(
            text = "Привет со второго экрана!",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
