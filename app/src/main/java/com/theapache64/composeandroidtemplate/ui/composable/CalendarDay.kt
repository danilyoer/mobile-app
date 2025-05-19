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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@Composable
fun CalendarDay(
    day: LocalDate,
    isSelected: Boolean,
    isCompleted: Boolean,
    isTarget: Boolean,
    onClick: () -> Unit,
    isSelectable: Boolean,
    isToday: Boolean,
    isErrorFlash: Boolean
) {
    Box(
        modifier = Modifier
            .testTag("CalendarDay_${day}")
            .aspectRatio(1f)
            .then(
                if (isSelectable && day != LocalDate.MIN) Modifier.clickable { onClick() }
                else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        if (day != LocalDate.MIN) {
            val animatedFontSize by animateFloatAsState(targetValue = if (isSelected) 18f else 14f)

            // Сначала рисуем ошибку, если есть
            if (isErrorFlash) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(0xFFFF5252), // Красная вспышка
                        radius = size.minDimension / 2.5f,
                        center = Offset(size.width / 2, size.height / 2),
                        alpha = 0.3f
                    )
                }
            } else if (isCompleted) {
                // Иначе — если выполнено, зелёный фон
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(0xFF4CAF50), // Зелёный выполненный
                        radius = size.minDimension / 2.5f,
                        center = Offset(size.width / 2, size.height / 2),
                        alpha = 0.3f
                    )
                }
            }

            // Целевой день (рамка)
            if (isTarget) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color.Red,
                        radius = size.minDimension / 3,
                        center = Offset(size.width / 2, size.height / 2),
                        style = Stroke(
                            width = 3f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f))
                        ),
                        alpha = 0.8f
                    )
                }
            }

            // Выделение выбранного дня
            if (isSelected) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(0xFF3700B3),
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

            // Само число
            Text(
                text = day.dayOfMonth.toString(),
                color = MaterialTheme.colors.onBackground,
                fontSize = animatedFontSize.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
