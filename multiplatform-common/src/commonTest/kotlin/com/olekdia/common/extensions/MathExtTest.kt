package com.olekdia.common.extensions

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MathExtTest {

    @Test
    fun roundTo() {
        assertEquals(30, 38 roundTo 15)
        assertEquals(50, 69 roundTo 25)
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