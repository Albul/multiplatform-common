package com.olekdia.common.extensions

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MathExtTest {

    @Test
    fun roundToInt() {
        assertEquals(30, 38 roundTo 15)
        assertEquals(50, 69 roundTo 25)
        assertEquals(69, 69 roundTo 1)
        assertEquals(69, 69 roundTo 3)
        assertEquals(68, 69 roundTo 2)
        assertEquals(-30, -32 roundTo 5)

        assertEquals(2147483646, Int.MAX_VALUE roundTo 2)
        assertEquals(2147483640, Int.MAX_VALUE roundTo 40)
        assertEquals(-2147483640, Int.MIN_VALUE roundTo 40)
        assertEquals(-2147483640, Int.MIN_VALUE roundTo -40)

        assertEquals(-30, -38 roundTo 15)
        assertEquals(-30, -38 roundTo -15)
    }

    @Test
    fun roundToLong() {
        assertEquals(30L, 38L roundTo 15L)
        assertEquals(50L, 69L roundTo 25L)
        assertEquals(69L, 69L roundTo 1L)
        assertEquals(69L, 69L roundTo 3L)
        assertEquals(68L, 69L roundTo 2L)
        assertEquals(9223372036854775806L, Long.MAX_VALUE roundTo 2L)
        assertEquals(9223372036854775800L, Long.MAX_VALUE roundTo 40L)
        assertEquals(-9223372036854775800L, Long.MIN_VALUE roundTo 40L)
        assertEquals(-9223372036854775800L, Long.MIN_VALUE roundTo -40L)

        assertEquals(-30L, -38L roundTo 15L)
        assertEquals(-30L, -38L roundTo -15L)
    }

    @Test
    fun roundToFloat() {
        assertEquals(32.0f, 32.5f roundTo 2f)
        assertEquals(32.5f, 32.5f roundTo 2.5f)
        assertEquals(29f, 32.5f roundTo 5.8f)
        assertEquals(-29f, -32.5f roundTo -5.8f)
        assertEquals(-29f, -32.5f roundTo 5.8f)
        assertEquals(-29f, -32.5f roundTo 5.8f)
        assertEquals(29f, 32.5f roundTo -5.8f)
        assertEquals(32.4f, 32.5f roundTo 0.2f)
        assertTrue((1f roundTo 0.3f) in 0.9f..0.91f)
        assertTrue((1f roundTo 0.1f) in 0.9f..1f)
        assertEquals(1f, 1f roundTo 1f)
    }

    @Test
    fun percentOf() {
        assertEquals(40, 50 percentOf 80)
        assertEquals(50, 25 percentOf 200)
        assertEquals(1089, 75 percentOf 1453)
        assertEquals(1453, 100 percentOf 1453)
        assertEquals(0, 0 percentOf 1453)
        assertEquals(-290, 20 percentOf -1453)
        assertEquals(429496729, 20 percentOf Int.MAX_VALUE)
        assertEquals(-429496729, 20 percentOf Int.MIN_VALUE)

        assertEquals(40L, 50 percentOf 80L)
        assertEquals(50L, 25 percentOf 200L)
        assertTrue((20 percentOf Long.MAX_VALUE) in 1844674407370955155L..1844674407370955165L)
        assertTrue((98 percentOf Long.MAX_VALUE) in 9038904596117680280L..9038904596117680300L)
    }

    @Test
    fun getRandomInt() {
        val start = -10
        val end = 10

        for (i in 0..100) {
            val r = getRandom(start, end)
            assertTrue(r <= end)
            assertTrue(r >= start)
        }
    }

    @Test
    fun getRandomInt_startEqualsEnd() {
        val start = 10
        val end = 10

        for (i in 0..100) {
            assertEquals(10, getRandom(start, end))
        }
    }

    @Test
    fun getRandomLong() {
        val start = -10L
        val end = 10L

        for (i in 0..100) {
            val r = getRandom(start, end)
            assertTrue(r <= end)
            assertTrue(r >= start)
        }
    }

    @Test
    fun getRandomFloat() {
        val start = -9.5F
        val end = 9.5F

        for (i in 0..100) {
            val r = getRandom(start, end)
            assertTrue(r <= end)
            assertTrue(r >= start)
        }
    }

    @Test
    fun getRandomDouble() {
        val start = -9.5
        val end = 9.5

        for (i in 0..100) {
            val r = getRandom(start, end)
            assertTrue(r <= end)
            assertTrue(r >= start)
        }
    }

    @Test
    fun getRandomBoolean() {
        var trueFound = false
        var falseFound = false

        for (i in 0..10) {
            if (getRandom()) {
                trueFound = true
            } else {
                falseFound = true
            }
        }

        assertTrue(trueFound)
        assertTrue(falseFound)
    }
}