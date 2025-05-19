package com.theapache64.composeandroidtemplate

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.theapache64.composeandroidtemplate.ui.screen.MainActivity
import org.junit.Rule
import org.junit.Test

class CategoryDropdownTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun allCategoryOptionsAreClickable() {
        // 1. Переходим на экран создания привычки
        rule.onNodeWithTag("CreateHabitButton")       // кнопка на SplashScreen :contentReference[oaicite:0]{index=0}:contentReference[oaicite:1]{index=1}
            .performClick()

        // 2. Убедимся, что CreateHabitScreen загружен
        rule.onNodeWithTag("CreateHabitScreenBox")    // контейнер CreateHabitScreen :contentReference[oaicite:2]{index=2}:contentReference[oaicite:3]{index=3}
            .assertIsDisplayed()

        // 3. Открываем dropdown и узнаём, сколько в нём опций
        rule.onNodeWithTag("КатегорияDropdown")       // тест-тег из ReusableDropdown для label="Категория" :contentReference[oaicite:4]{index=4}:contentReference[oaicite:5]{index=5}
            .performClick()
        Thread.sleep(1_000)
        val optionCount = rule
            .onAllNodesWithTag("КатегорияOption")     // общий тег для всех пунктов меню :contentReference[oaicite:6]{index=6}:contentReference[oaicite:7]{index=7}
            .fetchSemanticsNodes()
            .size

        // 4. Поочерёдно открываем меню и кликаем по каждой опции
        for (i in 0 until optionCount) {
            rule.onNodeWithTag("КатегорияDropdown")
                .performClick()
            Thread.sleep(1_000)
            rule.onAllNodesWithTag("КатегорияOption")[i]
                .performClick()
            Thread.sleep(1_000)
        }
        Thread.sleep(1_000)
    }
}
