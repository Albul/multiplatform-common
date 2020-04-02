package com.olekdia.common.extensions

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
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

//--------------------------------------------------------------------------------------------------
//  getRandom in range
//--------------------------------------------------------------------------------------------------

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
    fun getRandomInt_startBiggerEnd_fails() {
        val start = 10
        val end = 9

        assertFails {
            val r = getRandom(start, end)
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
    fun getRandomLong_startBiggerEnd_fails() {
        val start = 10L
        val end = 9L

        assertFails {
            val r = getRandom(start, end)
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
    fun getRandomFloat_startBiggerEnd_fails() {
        val start = 9.5F
        val end = 4.5F

        assertFails {
            val r = getRandom(start, end)
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
    fun getRandomDouble_startBiggerEnd_fails() {
        val start = 9.5
        val end = 4.5

        assertFails {
            val r = getRandom(start, end)
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

//--------------------------------------------------------------------------------------------------
//  getRandom from array
//--------------------------------------------------------------------------------------------------

    @Test
    fun getRandomIntArray() {
        val array = intArrayOf(
            3, 5, 8, 10, 16, 23
        )
        for (i in 0..100) {
            val r = getRandom(array)
            assertTrue(array.contains(r))
        }

        val arraySingle = intArrayOf(
            3
        )
        for (i in 0..10) {
            val r = getRandom(arraySingle)
            assertEquals(3, r)
        }
    }

    @Test
    fun getRandomIntArray_zeroSize_fails() {
        assertFails {
            val r = getRandom(intArrayOf())
        }
    }

    @Test
    fun getRandomLongArray() {
        val array = longArrayOf(
            3L, 5L, 8L, 10L, 16L, 23L
        )
        for (i in 0..100) {
            val r = getRandom(array)
            assertTrue(array.contains(r))
        }

        val arraySingle = longArrayOf(
            3L
        )
        for (i in 0..10) {
            val r = getRandom(arraySingle)
            assertEquals(3L, r)
        }
    }

    @Test
    fun getRandomLongArray_zeroSize_fails() {
        assertFails {
            val r = getRandom(longArrayOf())
        }
    }

    @Test
    fun getRandomFloatArray() {
        val array = floatArrayOf(
            3F, 5.5F, 8.1F, 10.4F, 16F, 23.23F
        )
        for (i in 0..100) {
            val r = getRandom(array)
            assertTrue(array.contains(r))
        }

        val arraySingle = floatArrayOf(
            3.3F
        )
        for (i in 0..10) {
            val r = getRandom(arraySingle)
            assertEquals(3.3F, r)
        }
    }

    @Test
    fun getRandomFloatArray_zeroSize_fails() {
        assertFails {
            val r = getRandom(floatArrayOf())
        }
    }

    @Test
    fun getRandomDoubleArray() {
        val array = doubleArrayOf(
            3.0, 5.5, 8.1, 10.4, 16.0, 23.23
        )
        for (i in 0..100) {
            val r = getRandom(array)
            assertTrue(array.contains(r))
        }

        val arraySingle = doubleArrayOf(
            3.3
        )
        for (i in 0..10) {
            val r = getRandom(arraySingle)
            assertEquals(3.3, r)
        }
    }

    @Test
    fun getRandomDoubleArray_zeroSize_fails() {
        assertFails {
            val r = getRandom(doubleArrayOf())
        }
    }

    @Test
    fun getRandomCharArray() {
        val array = charArrayOf(
            'a', 'c', 'r', 'ю', 'Ф'
        )
        for (i in 0..100) {
            val r = getRandom(array)
            assertTrue(array.contains(r))
        }

        val arraySingle = charArrayOf(
            '$'
        )
        for (i in 0..10) {
            val r = getRandom(arraySingle)
            assertEquals('$', r)
        }
    }

    @Test
    fun getRandomCharArray_zeroSize_fails() {
        assertFails {
            val r = getRandom(charArrayOf())
        }
    }

    @Test
    fun getRandomBooleanArray() {
        val array = booleanArrayOf(
            true, true, true, true, true
        )
        for (i in 0..100) {
            val r = getRandom(array)
            assertTrue(array.contains(r))
        }

        val arraySingle = booleanArrayOf(
            false
        )
        for (i in 0..10) {
            val r = getRandom(arraySingle)
            assertEquals(false, r)
        }
    }

    @Test
    fun getRandomBooleanArray_zeroSize_fails() {
        assertFails {
            val r = getRandom(booleanArrayOf())
        }
    }

    @Test
    fun getRandomStringArray() {
        val array = arrayOf<String>(
            "One", "Two", "Three", "Four"
        )
        for (i in 0..100) {
            val r = getRandom(array)
            assertTrue(array.contains(r))
        }

        val arraySingle = arrayOf<String>(
            "Random"
        )
        for (i in 0..10) {
            val r = getRandom(arraySingle)
            assertEquals("Random", r)
        }
    }

    @Test
    fun getRandomStringArray_zeroSize_fails() {
        assertFails {
            val r = getRandom(arrayOf<String>())
        }
    }

    @Test
    fun getRandomAnyArray() {
        val array = arrayOf<Any>(
            'a', "Random", 3, 4.5F, String.CASE_INSENSITIVE_ORDER
        )
        for (i in 0..100) {
            val r = getRandom(array)
            assertTrue(array.contains(r))
        }

        val arraySingle = arrayOf<Any>(
            "Random"
        )
        for (i in 0..10) {
            val r = getRandom(arraySingle)
            assertEquals("Random", r)
        }
    }

    @Test
    fun getRandomAnyArray_zeroSize_fails() {
        assertFails {
            val r = getRandom(arrayOf<Any>())
        }
    }

}