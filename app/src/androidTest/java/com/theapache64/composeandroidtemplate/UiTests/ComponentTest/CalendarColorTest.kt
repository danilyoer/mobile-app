package com.theapache64.composeandroidtemplate

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import java.io.File

@RunWith(AndroidJUnit4::class)
class CalendarColorTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun todayCellIsGreenishAfterClickingIt() {

        rule.runOnIdle {
            File(rule.activity.filesDir, "habits").deleteRecursively()
        }

        val habitName = "TestHabit${System.currentTimeMillis()}"
        rule.onNodeWithTag("CreateHabitButton").performClick()
        rule.onAllNodes(hasSetTextAction())[0].performTextInput(habitName)
        rule.onNodeWithTag("TimeField").performTextInput("1")
        rule.onNodeWithText("Сохранить").performClick()
        rule.waitForIdle()

        rule.onNodeWithTag("SecondScreenBox").assertIsDisplayed()
        rule.onNodeWithText("Название: $habitName").performClick()

        val todayTag = "CalendarDay_${LocalDate.now()}"
        rule.onNodeWithTag(todayTag)
            .assertExists()
            .performClick()
        rule.waitForIdle()

        val bitmap = rule.onNodeWithTag(todayTag)
            .captureToImage()
            .asAndroidBitmap()

        val centerX = bitmap.width / 2
        val centerY = bitmap.height / 2
        val pixel = bitmap.getPixel(centerX, centerY)

        val red   = (pixel shr 16) and 0xFF
        val green = (pixel shr  8) and 0xFF
        val blue  = (pixel       ) and 0xFF
        Thread.sleep(1_000)
        assert(green > red && green > blue) {
            "Ожидался зеленый оттенок, но получили R:$red G:$green B:$blue"
        }
        Thread.sleep(1_000)
    }
}
