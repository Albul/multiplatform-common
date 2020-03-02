package com.olekdia.common.extensions

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NumberExtTest {

    @Test
    fun zeroBased() {
        assertEquals(4, 5.zeroBased)
        assertEquals(1, 2.zeroBased)
        val list = listOf(1, 2, 3, 4, 5)

        assertEquals(list.lastIndex, list.size.zeroBased)
        assertEquals(list.lastIndex.oneBased, list.size)
    }

    @Test
    fun oneBased() {
        assertEquals(1, 0.oneBased)
        assertEquals(5, 4.oneBased)
        val list = listOf(1, 2, 3, 4, 5)
        assertEquals(list.lastIndex.oneBased, list.size)
    }

    @Test
    fun booleanToInt() {
        assertEquals(1, true.toInt())
        assertEquals(0, false.toInt())
    }

    @Test
    fun intToBoolean() {
        assertEquals(true, 5.toBoolean())
        assertEquals(true, 1.toBoolean())
        assertEquals(false, 0.toBoolean())
    }

    @Test
    fun sign() {
        assertEquals(0, 0.sign)
        assertEquals(0, 0L.sign)
        assertEquals(-1, (-1254).sign)
        assertEquals(-1, (-1254L).sign)
        assertEquals(-1, (-1).sign)
        assertEquals(1, 1.sign)
        assertEquals(1, 14325.sign)
        assertEquals(1, 543543526256565L.sign)
    }

    @Test
    fun positiveOrZero() {
        assertEquals(0, (-43).positiveOrZero())
        assertEquals(0, 0.positiveOrZero())
        assertEquals(46, 46.positiveOrZero())
        assertEquals(1, 1.positiveOrZero())
        assertEquals(1L, 1L.positiveOrZero())
        assertEquals(0, (-1254).positiveOrZero())
    }

    @Test
    fun negativeOrZero() {
        assertEquals(-43, (-43).negativeOrZero())
        assertEquals(0, 0.negativeOrZero())
        assertEquals(0, 46.negativeOrZero())
        assertEquals(0, 1.negativeOrZero())
        assertEquals(0, 1L.negativeOrZero())
        assertEquals(-1254, (-1254).negativeOrZero())
    }

    @Test
    fun isRoundedDouble() {
        assertTrue(1.0.isRounded())
        assertTrue(3.0.isRounded())
        assertTrue((-105.0).isRounded())
        assertFalse((-105.3).isRounded())

        assertFalse(0.00000000000000001.isRounded())
        assertFalse(0.9999999.isRounded())
        assertFalse(1.9999999.isRounded())
        assertFalse(99.9999999999999.isRounded())
        assertFalse((-99.9999999999999).isRounded())
    }

    @Test
    fun isRoundedFloat() {
        assertTrue(1.0f.isRounded())
        assertTrue(3.0f.isRounded())
        assertTrue((-105.0f).isRounded())
        assertFalse((-105.3f).isRounded())

        assertFalse(0.00000000000000001f.isRounded())
        assertFalse(0.9999999f.isRounded())
        assertFalse(1.9999999f.isRounded())
        assertFalse(99.9999f.isRounded())
        assertFalse((-99.9999f).isRounded())
    }

    @Test
    fun numberOfTrailingZeros() {
        assertEquals(5, 0b10100000.numberOfTrailingZeros())
        assertEquals(1, 0b1110.numberOfTrailingZeros())
        assertEquals(0, 0b1111111.numberOfTrailingZeros())
        assertEquals(32, 0.numberOfTrailingZeros())
        assertEquals(1, 0b10.numberOfTrailingZeros())
        assertEquals(10, 0b10000000000.numberOfTrailingZeros())
    }

    @Test
    fun numberOfDigits() {
        assertEquals(5, 12345.numberOfDigits())
        assertEquals(1, 1.numberOfDigits())
        assertEquals(1, 9.numberOfDigits())
        assertEquals(1, 0.numberOfDigits())
        assertEquals(2, 10.numberOfDigits())
        assertEquals(2, 99.numberOfDigits())
        assertEquals(3, 999.numberOfDigits())
        assertEquals(10, 1234567890.numberOfDigits())
        assertEquals(10, 1000000000.numberOfDigits())
    }

    @Test
    fun intPart() {
        assertEquals(0.0.intPart, 0)
        assertEquals(0.5555.intPart, 0)
        assertEquals(4.5.intPart, 4)
        assertEquals(4.9999.intPart, 4)
        assertEquals(4.00001.intPart, 4)
        assertEquals((-4.9999).intPart, -4)
        assertEquals((-4.0001).intPart, -4)


        assertEquals(0f.intPart, 0)
        assertEquals(0.5555f.intPart, 0)
        assertEquals(4.5f.intPart, 4)
        assertEquals(4.9999f.intPart, 4)
        assertEquals(4.00001f.intPart, 4)
        assertEquals((-4.9999f).intPart, -4)
        assertEquals((-4.0001f).intPart, -4)
    }
}