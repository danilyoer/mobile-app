package com.theapache64.composeandroidtemplate.UiTests.Navigation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import kotlin.random.Random
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeleteHabitTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun createHabit_openDetail_delete_and_backToSecond() {
        /* -------- Splash → CreateHabit -------- */
        rule.onNodeWithTag("CreateHabitButton").performClick()

        val uniqueName = "Habit_${Random.nextInt(1000, 9999)}"
        rule.onAllNodes(hasSetTextAction())[0].performTextInput(uniqueName) // название
        rule.onNodeWithTag("TimeField").performTextInput("10")              // время
        rule.onNodeWithText("Сохранить").performClick()

        /* -------- SecondScreen -------- */
        rule.waitForIdle()
        rule.onNodeWithTag("SecondScreenBox").assertIsDisplayed()
        rule.onNodeWithText(uniqueName, substring = true).performClick()

        /* -------- HabitDetail -------- */
        rule.waitForIdle()
        rule.onNodeWithText(uniqueName).assertIsDisplayed()
        Thread.sleep(1_000)
        // жмём «Удалить привычку»
        rule.onNodeWithText("Удалить привычку").performClick()

        /* -------- Вернулись на SecondScreen -------- */
        rule.waitForIdle()
        rule.onNodeWithTag("SecondScreenBox").assertIsDisplayed()
        Thread.sleep(1_000)
    }
}
