package com.theapache64.composeandroidtemplate

import org.junit.Assert.assertEquals
import org.junit.Test
import com.theapache64.composeandroidtemplate.ui.composable.getMaxDaysForFrequency


class FrequencyUtilsTest {

    @Test
    fun `daily frequency returns 365`() {
        assertEquals(365, getMaxDaysForFrequency("Ежедневно"))
    }
    @Test
    fun `every other day frequency returns 180`() {
        assertEquals(180, getMaxDaysForFrequency("Через день"))
    }
    @Test
    fun `weekly frequency returns 54`() {
        assertEquals(54, getMaxDaysForFrequency("Еженедельно"))
    }
    @Test
    fun `monthly frequency returns 12`() {
        assertEquals(12, getMaxDaysForFrequency("Раз в месяц"))
    }
    @Test
    fun `unknown frequency defaults to 365`() {
        assertEquals(365, getMaxDaysForFrequency("Некоторая частота"))
    }
}
