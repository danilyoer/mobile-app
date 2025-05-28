package com.theapache64.composeandroidtemplate.UiTests

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import org.junit.Rule
import org.junit.Test

class ErrorValidationTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun emptyNameShowsError() {
        // 1) Переходим на экран создания привычки
        rule.onNodeWithTag("CreateHabitButton").performClick()               // :contentReference[oaicite:0]{index=0}:contentReference[oaicite:1]{index=1}

        // 2) Жмём "Сохранить" не вводя ничего в поле имени
        rule.onNodeWithText("Сохранить").performClick()                     // :contentReference[oaicite:2]{index=2}:contentReference[oaicite:3]{index=3}

        // 3) Должен появиться диалог с ошибкой названия
        rule.onNodeWithText("Ошибка").assertIsDisplayed()
        rule.onNodeWithText("Название привычки должно содержать от 3 до 35 символов").assertIsDisplayed()

        // 4) Закрываем диалог и убеждаемся, что мы всё ещё на экране создания
        rule.onNodeWithText("ОК").performClick()
        rule.onNodeWithTag("CreateHabitScreenBox").assertIsDisplayed()
        Thread.sleep(1_000)
    }
}
