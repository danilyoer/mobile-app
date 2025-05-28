package com.theapache64.composeandroidtemplate.TimeFieldTests

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import com.theapache64.composeandroidtemplate.ui.composable.isValidTimeInput

class TimeFieldUnitTestMain {


    @Test
    fun `maxDays 5 accepts '5'`() {
        // Граничное значение — должно быть валидно
        assertTrue(isValidTimeInput("5", 5))
    }
    @Test
    fun `maxDays 5 rejects '6'`() {
        // Значение вне диапазона — не валидно
        assertFalse(isValidTimeInput("6", 5))
    }
    @Test
    fun `maxDays 5 allows empty string`() {
        // Пустая строка считается допустимой
        assertTrue(isValidTimeInput("", 5))
    }
    @Test
    fun `maxDays 5 rejects non-numeric`() {
        // Любой не-цифровой ввод должен быть невалиден
        assertFalse(isValidTimeInput("a", 5))
        assertFalse(isValidTimeInput("#", 5))
    }
}
