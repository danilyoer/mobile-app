package com.theapache64.composeandroidtemplate

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import kotlin.random.Random
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HabitDetailBackToSecondTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun detail_back_returnsToSecond() {
        /* ---------- Splash → CreateHabit ---------- */
        rule.onNodeWithTag("CreateHabitButton").performClick()

        val name = "Habit_${Random.nextInt(1000, 9999)}"
        rule.onAllNodes(hasSetTextAction())[0].performTextInput(name)       // название
        rule.onNodeWithTag("TimeField").performTextInput("10")              // время
        rule.onNodeWithText("Сохранить").performClick()

        /* ---------- SecondScreen ---------- */
        rule.waitForIdle()
        rule.onNodeWithTag("SecondScreenBox").assertIsDisplayed()
        rule.onNodeWithText(name, substring = true).performClick()

        /* ---------- HabitDetail ---------- */
        rule.waitForIdle()
        rule.onNodeWithText(name).assertIsDisplayed()                       // убедились, что мы внутри
        Thread.sleep(1_000)
        /* ---------- нажимаем «Назад» ---------- */
        rule.onNodeWithText("Назад", substring = true).performClick()
        rule.waitForIdle()
        Thread.sleep(1_000)
        /* ---------- снова SecondScreen ---------- */
        rule.onNodeWithTag("SecondScreenBox").assertIsDisplayed()
        Thread.sleep(1_000)
    }
}
