package com.theapache64.composeandroidtemplate.UiTests.Navigation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class CreateHabitSaveTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun createHabit_and_navigate_to_secondScreen() {
        // переход Splash → CreateHabit
        rule.onNodeWithTag("CreateHabitButton").performClick()
        Thread.sleep(1_000)

        // заполняем поля (первые два TextField'а на экране)
        val uniqueName = "Habit_${Random.nextInt(1000, 9999)}"
        rule.onAllNodes(hasSetTextAction())[0].performTextInput(uniqueName)
        Thread.sleep(1_000)

        rule.onNodeWithTag("TimeField").performTextInput("10")
        Thread.sleep(1_000)
        // Сохраняем введенный текст
        rule.onNodeWithText("Сохранить").performClick()
        Thread.sleep(1_000)

        // убеждаемся, что открылась SecondScreen
        rule.waitForIdle()
        rule.onNodeWithTag("SecondScreenBox").assertIsDisplayed()
        Thread.sleep(3_000)
    }
}
