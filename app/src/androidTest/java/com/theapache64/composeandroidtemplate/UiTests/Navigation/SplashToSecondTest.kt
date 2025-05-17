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
class SplashToSecondTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun splash_to_secondScreen() {
        // 1) жмём «Мои привычки» на SplashScreen
        rule.onNodeWithTag("HabitListButton")
            .performClick()

        // 2) убеждаемся, что появился SecondScreen
        rule.onNodeWithTag("SecondScreenBox")
            .assertIsDisplayed()


        Thread.sleep(2_000)
    }
}
