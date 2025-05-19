package com.theapache64.composeandroidtemplate

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import org.junit.Rule
import org.junit.Test

class FrequencyDropdownTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun allFrequencyOptionsAreClickable() {
        // 1. Переходим на экран создания привычки
        rule.onNodeWithTag("CreateHabitButton")       // кнопка из SplashScreen :contentReference[oaicite:0]{index=0}:contentReference[oaicite:1]{index=1}
            .performClick()

        // 2. Убедимся, что CreateHabitScreen загружен
        rule.onNodeWithTag("CreateHabitScreenBox")    // контейнер экрана :contentReference[oaicite:2]{index=2}:contentReference[oaicite:3]{index=3}
            .assertIsDisplayed()
        Thread.sleep(1_000)
        // 3. Открываем dropdown и замеряем число опций
        rule.onNodeWithTag("ЧастотаDropdown")         // тест-тег из ReusableDropdown.kt :contentReference[oaicite:4]{index=4}:contentReference[oaicite:5]{index=5}
            .performClick()
        val optionCount = rule
            .onAllNodesWithTag("ЧастотаOption")       // общий тег на всех пунктах меню :contentReference[oaicite:6]{index=6}:contentReference[oaicite:7]{index=7}
            .fetchSemanticsNodes()
            .size
        Thread.sleep(1_000)
        // 4. Для каждого индекса: открываем меню и кликаем по i-й опции
        for (i in 0 until optionCount) {
            rule.onNodeWithTag("ЧастотаDropdown")
                .performClick()
            Thread.sleep(1_000)
            rule.onAllNodesWithTag("ЧастотаOption")[i]
                .performClick()

            Thread.sleep(1_000)
        }
    }
}
