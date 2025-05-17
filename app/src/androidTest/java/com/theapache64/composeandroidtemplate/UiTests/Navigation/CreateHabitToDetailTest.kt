package com.theapache64.composeandroidtemplate.UiTests.Navigation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import kotlin.random.Random
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateHabitToDetailTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun createHabit_and_open_its_detail() {
        /* ---------- Splash → CreateHabit ---------- */
        rule.onNodeWithTag("CreateHabitButton").performClick()

        /* ---------- вводим данные ---------- */
        val uniqueName = "Habit_${Random.nextInt(1000, 9999)}"

        // поле «Название»
        rule.onAllNodes(hasSetTextAction())[0]
            .performTextInput(uniqueName)

        // поле «Время выполнения»
        rule.onNodeWithTag("TimeField")
            .performTextInput("10")

        // Сохраняем
        rule.onNodeWithText("Сохранить").performClick()

        /* ---------- SecondScreen ---------- */
        rule.waitForIdle()                                       // дождаться навигации
        rule.onNodeWithTag("SecondScreenBox")
            .assertIsDisplayed()
        Thread.sleep(1_000)
        // карточка с нашей привычкой появилась
        rule.onNodeWithText(uniqueName, substring = true)
            .assertExists()
            .performClick()                                      // открываем детали
        Thread.sleep(1_000)
        /* ---------- HabitDetail ---------- */
        rule.waitForIdle()                                       // дождаться перехода
        rule.onNodeWithText(uniqueName)                          // имя в шапке
            .assertExists()
            .assertIsDisplayed()
    }
}
