package com.theapache64.composeandroidtemplate

import org.junit.Assert.assertEquals
import org.junit.Test
import com.theapache64.composeandroidtemplate.ui.composable.limitLength
import com.theapache64.composeandroidtemplate.ui.composable.DEFAULT_MAX_LENGTH

class LimitLengthTest {
    @Test
    fun `default maxLength remains 35`() {
        val input = "X".repeat(50)
        val expected = "X".repeat(35)
        assertEquals(
            "limitLength(input) must input 35 sym",
            expected,
            limitLength(input)
        )
    }
    @Test
    fun `input shorter than max remains unchanged`() {
        val input = "Short name"
        assertEquals(input, limitLength(input, maxLength = DEFAULT_MAX_LENGTH))
    }

    @Test
    fun `input exactly max length remains unchanged`() {
        val input = "A".repeat(DEFAULT_MAX_LENGTH)
        assertEquals(input, limitLength(input, maxLength = DEFAULT_MAX_LENGTH))
    }

    @Test
    fun `input longer than max is truncated`() {
        val input = "B".repeat(50)
        val expected = "B".repeat(DEFAULT_MAX_LENGTH)
        assertEquals(expected, limitLength(input, maxLength = DEFAULT_MAX_LENGTH))
    }

    @Test
    fun `empty input returns empty`() {
        assertEquals("", limitLength("", maxLength = DEFAULT_MAX_LENGTH))
    }

}
