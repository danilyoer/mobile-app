package com.theapache64.composeandroidtemplate

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed   // есть даже в самых ранних вер­сиях Compose Test
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashToCreateTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun splash_to_createHabit() {
        // 1. жмём «Создать привычку» на Splash-экране
        rule.onNodeWithTag("CreateHabitButton")
            .performClick()

        // 2. ждём таймер (1,5 с) из SplashViewModel
        //Thread.sleep(2_000)

        // 3. убеждаемся, что открыт экран создания привычки
        rule.onNodeWithTag("CreateHabitScreenBox")
            .assertIsDisplayed()

        Thread.sleep(2_000)
    }
}
