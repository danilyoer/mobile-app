package com.theapache64.composeandroidtemplate.TimeFieldTests

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import com.theapache64.composeandroidtemplate.ui.composable.isValidTimeInput


class TimeFieldUnitTest {

    // maxDays = 5
    @Test fun maxDays5_input1_valid() = assertTrue(isValidTimeInput("1", 5))
    @Test fun maxDays5_input5_valid() = assertTrue(isValidTimeInput("5", 5))
    @Test fun maxDays5_inputEmpty_valid() = assertTrue(isValidTimeInput("", 5))
    @Test fun maxDays5_input05_valid() = assertTrue(isValidTimeInput("05", 5))

    @Test fun maxDays5_input0_invalid() = assertFalse(isValidTimeInput("0", 5))
    @Test fun maxDays5_input6_invalid() = assertFalse(isValidTimeInput("6", 5))
    @Test fun maxDays5_inputMinus1_invalid() = assertFalse(isValidTimeInput("-1", 5))
    @Test fun maxDays5_inputA_invalid() = assertFalse(isValidTimeInput("a", 5))
    @Test fun maxDays5_inputSpace5_invalid() = assertFalse(isValidTimeInput(" 5", 5))
    @Test fun maxDays5_input5dot0_invalid() = assertFalse(isValidTimeInput("5.0", 5))

    // maxDays = 55
    @Test fun maxDays55_input1_valid() = assertTrue(isValidTimeInput("1", 55))
    @Test fun maxDays55_input55_valid() = assertTrue(isValidTimeInput("55", 55))
    @Test fun maxDays55_input54_valid() = assertTrue(isValidTimeInput("54", 55))
    @Test fun maxDays55_input10_valid() = assertTrue(isValidTimeInput("10", 55))
    @Test fun maxDays55_input0055_valid() = assertTrue(isValidTimeInput("0055", 55))
    @Test fun maxDays55_inputEmpty_valid() = assertTrue(isValidTimeInput("", 55))

    @Test fun maxDays55_input56_invalid() = assertFalse(isValidTimeInput("56", 55))
    @Test fun maxDays55_input99_invalid() = assertFalse(isValidTimeInput("99", 55))
    @Test fun maxDays55_input100_invalid() = assertFalse(isValidTimeInput("100", 55))
    @Test fun maxDays55_inputMinus5_invalid() = assertFalse(isValidTimeInput("-5", 55))

    // maxDays = 222
    @Test fun maxDays222_input1_valid() = assertTrue(isValidTimeInput("1", 222))
    @Test fun maxDays222_input222_valid() = assertTrue(isValidTimeInput("222", 222))
    @Test fun maxDays222_input221_valid() = assertTrue(isValidTimeInput("221", 222))
    @Test fun maxDays222_input100_valid() = assertTrue(isValidTimeInput("100", 222))
    @Test fun maxDays222_input0222_valid() = assertTrue(isValidTimeInput("0222", 222))
    @Test fun maxDays222_inputEmpty_valid() = assertTrue(isValidTimeInput("", 222))

    @Test fun maxDays222_input223_invalid() = assertFalse(isValidTimeInput("223", 222))
    @Test fun maxDays222_input300_invalid() = assertFalse(isValidTimeInput("300", 222))
    @Test fun maxDays222_input999_invalid() = assertFalse(isValidTimeInput("999", 222))
    @Test fun maxDays222_inputMinus10_invalid() = assertFalse(isValidTimeInput("-10", 222))

    // maxDays = 0
    @Test fun maxDays0_inputEmpty_valid() = assertTrue(isValidTimeInput("", 0))

    @Test fun maxDays0_input0_invalid() = assertFalse(isValidTimeInput("0", 0))
    @Test fun maxDays0_input1_invalid() = assertFalse(isValidTimeInput("1", 0))
    @Test fun maxDays0_input99_invalid() = assertFalse(isValidTimeInput("99", 0))
    @Test fun maxDays0_inputA_invalid() = assertFalse(isValidTimeInput("a", 0))
}

//deeepseek проигнорировал импорты; справился со 2 попытки, атомарные тесты вывел в одну строку; проблем с неймингом не было; вывел тесты в од