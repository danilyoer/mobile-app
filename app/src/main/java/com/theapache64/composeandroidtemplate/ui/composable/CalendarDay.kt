package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@Composable
fun CalendarDay(
    day: LocalDate,
    isSelected: Boolean,
    isCompleted: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(enabled = day != LocalDate.MIN) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (day != LocalDate.MIN) {
            val animatedFontSize by animateFloatAsState(targetValue = if (isSelected) 18f else 14f)

            if (isCompleted) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(0xFF4CAF50), // Зеленый цвет для выполнения
                        radius = size.minDimension / 2.5f,
                        center = Offset(size.width / 2, size.height / 2),
                        alpha = 0.3f // чуть прозрачный
                    )
                }
            }

            if (isSelected) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(0xFF3700B3), // Фиолетовый пунктир
                        radius = size.minDimension / 2.5f,
                        center = Offset(size.width / 2, size.height / 2),
                        style = Stroke(
                            width = 4f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                        ),
                        alpha = 0.5f
                    )
                }
            }

            Text(
                text = day.dayOfMonth.toString(),
                color = MaterialTheme.colors.onBackground,
                fontSize = animatedFontSize.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
