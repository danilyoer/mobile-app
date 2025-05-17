package com.theapache64.composeandroidtemplate.UiTests.Navigation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateBackToSplashTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()
    @Test
    fun fromCreateHabit_back_returnsToSplash() {
        // Splash → CreateHabit
        rule.onNodeWithTag("CreateHabitButton").performClick()
        Thread.sleep(1_000)
        // кликаем по "Назад" (кликабельный Text)
        rule.onNodeWithTag("CreateBackButton").performClick()
        Thread.sleep(1_000)

        Thread.sleep(1_000)
        // снова Splash-экран
        rule.onNodeWithTag("SplashScreenBox").assertIsDisplayed()

        Thread.sleep(1_000)

    }



}
