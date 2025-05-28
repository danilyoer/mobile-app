package com.theapache64.composeandroidtemplate.UiTests

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import org.junit.Rule
import org.junit.Test
import java.io.File

class ErrorValidationTimeTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun emptyTimeShowsError() {
        // ─── Подготовка: очищаем папку с привычками ───
        rule.runOnIdle {
            File(rule.activity.filesDir, "habits").deleteRecursively()
        }

        // 1) Переходим на экран создания привычки
        rule.onNodeWithTag("CreateHabitButton")
            .performClick()                                             // :contentReference[oaicite:6]{index=6}:contentReference[oaicite:7]{index=7}

        // 2) Вводим корректное имя (чтобы не сработала валидация имени)
        rule.onNodeWithTag("HabitNameField")
            .performTextInput("MyHabit")                               // :contentReference[oaicite:8]{index=8}:contentReference[oaicite:9]{index=9}

        // 3) Поле времени остаётся пустым; сразу жмём «Сохранить»
        rule.onNodeWithText("Сохранить")
            .performClick()                                            // :contentReference[oaicite:10]{index=10}:contentReference[oaicite:11]{index=11}

        // 4) Проверяем, что показался диалог с заголовком «Ошибка»
        rule.onNodeWithText("Ошибка")
            .assertIsDisplayed()

        // 5) И с текстом про диапазон дней (для дневной частоты maxDays = 365)
        rule.onNodeWithText("Время выполнения должно быть числом от 1 до 365")
            .assertIsDisplayed()
        Thread.sleep(1_000)
        // 6) Закрываем диалог и убеждаемся, что остались на экране создания
        rule.onNodeWithText("ОК")
            .performClick()
        rule.onNodeWithTag("CreateHabitScreenBox")
            .assertIsDisplayed()
        Thread.sleep(1_000)
    }
}
