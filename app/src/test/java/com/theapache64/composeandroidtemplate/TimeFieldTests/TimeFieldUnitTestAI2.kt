package com.theapache64.composeandroidtemplate.TimeFieldTests


import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import com.theapache64.composeandroidtemplate.ui.composable.isValidTimeInput

class TimeFieldUnitTestMax5 {
    @Test
    fun `maxDays 5 accepts '1'`() {
        assertTrue(isValidTimeInput("1", 5))
    }

    @Test
    fun `maxDays 5 accepts '5'`() {
        assertTrue(isValidTimeInput("5", 5))
    }

    @Test
    fun `maxDays 5 accepts empty string`() {
        assertTrue(isValidTimeInput("", 5))
    }

    @Test
    fun `maxDays 5 rejects '0'`() {
        assertFalse(isValidTimeInput("0", 5))
    }

    @Test
    fun `maxDays 5 rejects '6'`() {
        assertFalse(isValidTimeInput("6", 5))
    }

    @Test
    fun `maxDays 5 rejects '-1'`() {
        assertFalse(isValidTimeInput("-1", 5))
    }

    @Test
    fun `maxDays 5 accepts '05'`() {
        assertTrue(isValidTimeInput("05", 5))
    }

    @Test
    fun `maxDays 5 rejects 'a'`() {
        assertFalse(isValidTimeInput("a", 5))
    }

    @Test
    fun `maxDays 5 rejects ' 5'`() {
        assertFalse(isValidTimeInput(" 5", 5))
    }

    @Test
    fun `maxDays 5 rejects '5_0'`() {
        assertFalse(isValidTimeInput("5.0", 5))
    }
}

class TimeFieldUnitTestMax55 {
    @Test
    fun `maxDays 55 accepts '1'`() {
        assertTrue(isValidTimeInput("1", 55))
    }

    @Test
    fun `maxDays 55 accepts '55'`() {
        assertTrue(isValidTimeInput("55", 55))
    }

    @Test
    fun `maxDays 55 accepts '54'`() {
        assertTrue(isValidTimeInput("54", 55))
    }

    @Test
    fun `maxDays 55 accepts '10'`() {
        assertTrue(isValidTimeInput("10", 55))
    }

    @Test
    fun `maxDays 55 accepts empty string`() {
        assertTrue(isValidTimeInput("", 55))
    }

    @Test
    fun `maxDays 55 rejects '56'`() {
        assertFalse(isValidTimeInput("56", 55))
    }

    @Test
    fun `maxDays 55 rejects '99'`() {
        assertFalse(isValidTimeInput("99", 55))
    }

    @Test
    fun `maxDays 55 rejects '100'`() {
        assertFalse(isValidTimeInput("100", 55))
    }

    @Test
    fun `maxDays 55 accepts '0055'`() {
        assertTrue(isValidTimeInput("0055", 55))
    }

    @Test
    fun `maxDays 55 rejects '-5'`() {
        assertFalse(isValidTimeInput("-5", 55))
    }
}

class TimeFieldUnitTestMax222 {
    @Test
    fun `maxDays 222 accepts '1'`() {
        assertTrue(isValidTimeInput("1", 222))
    }

    @Test
    fun `maxDays 222 accepts '222'`() {
        assertTrue(isValidTimeInput("222", 222))
    }

    @Test
    fun `maxDays 222 accepts '221'`() {
        assertTrue(isValidTimeInput("221", 222))
    }

    @Test
    fun `maxDays 222 accepts '100'`() {
        assertTrue(isValidTimeInput("100", 222))
    }

    @Test
    fun `maxDays 222 accepts empty string`() {
        assertTrue(isValidTimeInput("", 222))
    }

    @Test
    fun `maxDays 222 rejects '223'`() {
        assertFalse(isValidTimeInput("223", 222))
    }

    @Test
    fun `maxDays 222 rejects '300'`() {
        assertFalse(isValidTimeInput("300", 222))
    }

    @Test
    fun `maxDays 222 rejects '999'`() {
        assertFalse(isValidTimeInput("999", 222))
    }

    @Test
    fun `maxDays 222 accepts '0222'`() {
        assertTrue(isValidTimeInput("0222", 222))
    }

    @Test
    fun `maxDays 222 rejects '-10'`() {
        assertFalse(isValidTimeInput("-10", 222))
    }
}

class TimeFieldUnitTestMax0 {
    @Test
    fun `maxDays 0 rejects '0'`() {
        assertFalse(isValidTimeInput("0", 0))
    }

    @Test
    fun `maxDays 0 rejects '1'`() {
        assertFalse(isValidTimeInput("1", 0))
    }

    @Test
    fun `maxDays 0 rejects '99'`() {
        assertFalse(isValidTimeInput("99", 0))
    }

    @Test
    fun `maxDays 0 rejects 'a'`() {
        assertFalse(isValidTimeInput("a", 0))
    }

    @Test
    fun `maxDays 0 accepts empty string`() {
        assertTrue(isValidTimeInput("", 0))
    }
}


//qwen справился с 2 попытки, была ошибка в нейминге, разбил на 3 отдельных класса