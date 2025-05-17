package com.theapache64.composeandroidtemplate.UiTests.Navigation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random
import java.io.File

class FullNavigationFlowTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun full_navigation_flow() {
        // ───── подготовка окружения ─────
        // Чистим папку habits, чтобы список был пустой
        rule.runOnIdle {
            File(rule.activity.filesDir, "habits").deleteRecursively()
        }

        // ═════════ 1. Splash → Second (пустой список) ═════════
        rule.onNodeWithTag("HabitListButton").performClick()                           // «Мои привычки» :contentReference[oaicite:0]{index=0}:contentReference[oaicite:1]{index=1}
        rule.waitForIdle()
        rule.onNodeWithTag("SecondScreenBox").assertIsDisplayed()                      // экран открыт :contentReference[oaicite:2]{index=2}:contentReference[oaicite:3]{index=3}
        // убеждаемся, что ни одной карточки нет
        rule.onAllNodesWithText("Название:").assertCountEquals(0)

        // Назад к Splash
        rule.onNodeWithText("Назад").performClick()

        // ═════════ 2. Splash → Create → Cancel ═════════
        rule.onNodeWithTag("CreateHabitButton").performClick()                         // «Создать привычку» :contentReference[oaicite:4]{index=4}:contentReference[oaicite:5]{index=5}
        rule.onNodeWithTag("CancelHabitScreenBox").performClick()                      // «Отмена» (тег из ActionButtons)

        // ═════════ 3. Splash → Create → BackArrow ═════════
        rule.onNodeWithTag("CreateHabitButton").performClick()
        rule.onNodeWithTag("CreateBackButton").performClick()                          // «Назад» в BackButtonRow

        // ═════════ 4. Splash → Create → Save (habit-1) ═════════
        val habit1 = "Habit_${Random.nextInt(1000,9999)}"
        rule.onNodeWithTag("CreateHabitButton").performClick()
        rule.onAllNodes(hasSetTextAction())[0].performTextInput(habit1)                // имя
        rule.onNodeWithTag("TimeField").performTextInput("5")                          // время :contentReference[oaicite:6]{index=6}:contentReference[oaicite:7]{index=7}
        rule.onNodeWithText("Сохранить").performClick()                                // SAVE в ActionButtons
        rule.waitForIdle()
        rule.onNodeWithTag("SecondScreenBox").assertIsDisplayed()

        // Назад к Splash
        rule.onNodeWithText("Назад").performClick()
        rule.onNodeWithTag("SplashScreenBox").assertIsDisplayed()                      // :contentReference[oaicite:8]{index=8}:contentReference[oaicite:9]{index=9}

        // ═════════ 5. Splash → Create → Save (habit-2) ═════════
        val habit2 = "Habit_${Random.nextInt(1000,9999)}"
        rule.onNodeWithTag("CreateHabitButton").performClick()
        rule.onAllNodes(hasSetTextAction())[0].performTextInput(habit2)
        rule.onNodeWithTag("TimeField").performTextInput("3")
        rule.onNodeWithText("Сохранить").performClick()
        rule.waitForIdle()

        // Открываем detail второй (самой свежей) привычки
        rule.onNodeWithText("Название: $habit2").performClick()
        rule.onNodeWithText(habit2).assertIsDisplayed()                                // заголовок в Detail экранe

        // Удаляем habit-2
        rule.onNodeWithText("Удалить привычку").performClick()                         // кнопка delete :contentReference[oaicite:10]{index=10}:contentReference[oaicite:11]{index=11}
        rule.waitForIdle()
        rule.onNodeWithTag("SecondScreenBox").assertIsDisplayed()

        // Открываем detail первой привычки
        rule.onNodeWithText("Название: $habit1").performClick()
        rule.onNodeWithText(habit1).assertIsDisplayed()

        // ← Назад к Second
        rule.onNodeWithText("← Назад").performClick()

        // Назад к Splash
        rule.onNodeWithText("Назад").performClick()
        rule.onNodeWithTag("SplashScreenBox").assertIsDisplayed()                      // финальная проверка
    }
}
