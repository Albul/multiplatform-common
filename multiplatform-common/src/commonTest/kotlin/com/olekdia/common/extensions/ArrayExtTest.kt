package com.olekdia.common.extensions

import com.olekdia.common.INVALID
import kotlin.test.*

class ArrayExtTest {

    lateinit var intArray: IntArray
    lateinit var longArray: LongArray
    lateinit var shortArray: ShortArray
    lateinit var byteArray: ByteArray
    lateinit var stringArray: Array<String>

    @BeforeTest
    fun setup() {
        intArray = intArrayOf(1, 3, 5, 7, 12, 25, 100)
        longArray = longArrayOf(1L, 3L, 5L, 7L, 12L, 25L, 100L)
        shortArray = shortArrayOf(1, 3, 5, 7, 12, 25, 100)
        byteArray = byteArrayOf(1, 3, 5, 7, 12, 25, 100)
        stringArray = arrayOf("1", "three", "5", "seven", "12", "twenty five", "100")
    }

//--------------------------------------------------------------------------------------------------
//  sumBy
//--------------------------------------------------------------------------------------------------

    class Entry(val value: Long)

    @Test
    fun symBy_longList_returnCorrectResult() {
        assertEquals(
            22L, listOf<Long>(3L, 8L, -2L, 14L, -1L).sumBy { it }
        )
    }

    @Test
    fun symBy_entryList_returnCorrectResult() {
        assertEquals(
            28L,
            listOf<Entry>(Entry(-2L), Entry(15L), Entry(4L), Entry(11L))
                .sumBy { it.value }
        )
    }


//--------------------------------------------------------------------------------------------------
//  binarySearch
//--------------------------------------------------------------------------------------------------

    @Test
    fun binarySearch_intArray_invalidValue_returnInvalidValue() {
        assertTrue(intArray.binarySearch(2) < 0)
    }

    @Test
    fun binarySearch_intArray_validValue_valueIsFound() {
        assertEquals(1, intArray.binarySearch(3))
        assertEquals(0, intArray.binarySearch(1))
        assertEquals(6, intArray.binarySearch(100))
    }

    @Test
    fun binarySearch_intArray_validValue_insideRange_valueIsFound() {
        assertEquals(3, intArray.binarySearch(7, fromIndex = 2, toIndex = 5))
        assertEquals(0, intArray.binarySearch(1, fromIndex = 0, toIndex = 3))
        assertEquals(6, intArray.binarySearch(100, fromIndex = 5))
    }

    @Test
    fun binarySearch_intArray_validValue_outsideRange_valueIsNotFound() {
        assertTrue(intArray.binarySearch(7, fromIndex = 4, toIndex = 6) < 0)
        assertTrue(intArray.binarySearch(1, fromIndex = 1, toIndex = 3) < 0)
        assertTrue(intArray.binarySearch(100, toIndex = 5) < 0)
    }

    @Test
    fun binarySearch_intArray_invalidIndex_throwsException() {
        assertEquals(
            INVALID,
            intArray.binarySearch(3, fromIndex = -1)
        )
        assertEquals(
            INVALID,
            intArray.binarySearch(3, fromIndex = 100)
        )
        assertEquals(
            INVALID,
            intArray.binarySearch(3, toIndex = -1)
        )
        assertEquals(
            INVALID,
            intArray.binarySearch(3, toIndex = 100)
        )
        assertEquals(
            INVALID,
            intArray.binarySearch(3, fromIndex = 4, toIndex = 2)
        )
    }

    @Test
    fun binarySearch_longArray_invalidValue_returnInvalidValue() {
        assertTrue(longArray.binarySearch(2L) < 0)
    }

    @Test
    fun binarySearch_longArray_validValue_valueIsFound() {
        assertEquals(1, longArray.binarySearch(3L))
        assertEquals(0, longArray.binarySearch(1L))
        assertEquals(6, longArray.binarySearch(100L))
    }

    @Test
    fun binarySearch_longArray_validValue_insideRange_valueIsFound() {
        assertEquals(3, longArray.binarySearch(7L, fromIndex = 2, toIndex = 5))
        assertEquals(0, longArray.binarySearch(1L, fromIndex = 0, toIndex = 3))
        assertEquals(6, longArray.binarySearch(100L, fromIndex = 5))
    }

    @Test
    fun binarySearch_longArray_validValue_outsideRange_valueIsNotFound() {
        assertTrue(longArray.binarySearch(7L, fromIndex = 4, toIndex = 6) < 0)
        assertTrue(longArray.binarySearch(1L, fromIndex = 1, toIndex = 3) < 0)
        assertTrue(longArray.binarySearch(100L, toIndex = 5) < 0)
    }

    @Test
    fun binarySearch_longArray_invalidIndex_throwsException() {
        assertEquals(
            INVALID,
            longArray.binarySearch(3L, fromIndex = -1)
        )
        assertEquals(
            INVALID,
            longArray.binarySearch(3L, fromIndex = 100)
        )
        assertEquals(
            INVALID,
            longArray.binarySearch(3L, toIndex = -1)
        )
        assertEquals(
            INVALID,
            longArray.binarySearch(3L, toIndex = 100)
        )
        assertEquals(
            INVALID,
            longArray.binarySearch(3L, fromIndex = 4, toIndex = 2)
        )
    }

    @Test
    fun binarySearch_shortArray_invalidValue_returnInvalidValue() {
        assertTrue(shortArray.binarySearch(2) < 0)
    }

    @Test
    fun binarySearch_shortArray_validValue_valueIsFound() {
        assertEquals(1, shortArray.binarySearch(3))
        assertEquals(0, shortArray.binarySearch(1))
        assertEquals(6, shortArray.binarySearch(100))
    }

    @Test
    fun binarySearch_shortArray_validValue_insideRange_valueIsFound() {
        assertEquals(3, shortArray.binarySearch(7, fromIndex = 2, toIndex = 5))
        assertEquals(0, shortArray.binarySearch(1, fromIndex = 0, toIndex = 3))
        assertEquals(6, shortArray.binarySearch(100, fromIndex = 5))
    }

    @Test
    fun binarySearch_shortArray_validValue_outsideRange_valueIsNotFound() {
        assertTrue(shortArray.binarySearch(7, fromIndex = 4, toIndex = 6) < 0)
        assertTrue(shortArray.binarySearch(1, fromIndex = 1, toIndex = 3) < 0)
        assertTrue(shortArray.binarySearch(100, toIndex = 5) < 0)
    }

    @Test
    fun binarySearch_shortArray_invalidIndex_throwsException() {
        assertEquals(
            INVALID,
            shortArray.binarySearch(3, fromIndex = -1)
        )
        assertEquals(
            INVALID,
            shortArray.binarySearch(3, fromIndex = 100)
        )
        assertEquals(
            INVALID,
            shortArray.binarySearch(3, toIndex = -1)
        )
        assertEquals(
            INVALID,
            shortArray.binarySearch(3, toIndex = 100)
        )
        assertEquals(
            INVALID,
            shortArray.binarySearch(3, fromIndex = 4, toIndex = 2)
        )
    }

    @Test
    fun binarySearch_byteArray_invalidValue_returnInvalidValue() {
        assertTrue(byteArray.binarySearch(2) < 0)
    }

    @Test
    fun binarySearch_byteArray_validValue_valueIsFound() {
        assertEquals(1, byteArray.binarySearch(3))
        assertEquals(0, byteArray.binarySearch(1))
        assertEquals(6, byteArray.binarySearch(100))
    }

    @Test
    fun binarySearch_byteArray_validValue_insideRange_valueIsFound() {
        assertEquals(3, byteArray.binarySearch(7, fromIndex = 2, toIndex = 5))
        assertEquals(0, byteArray.binarySearch(1, fromIndex = 0, toIndex = 3))
        assertEquals(6, byteArray.binarySearch(100, fromIndex = 5))
    }

    @Test
    fun binarySearch_byteArray_validValue_outsideRange_valueIsNotFound() {
        assertTrue(byteArray.binarySearch(7, fromIndex = 4, toIndex = 6) < 0)
        assertTrue(byteArray.binarySearch(1, fromIndex = 1, toIndex = 3) < 0)
        assertTrue(byteArray.binarySearch(100, toIndex = 5) < 0)
    }

    @Test
    fun binarySearch_byteArray_invalidIndex_throwsException() {
        assertEquals(
            INVALID,
            byteArray.binarySearch(3, fromIndex = -1)
        )
        assertEquals(
            INVALID,
            byteArray.binarySearch(3, fromIndex = 100)
        )
        assertEquals(
            INVALID,
            byteArray.binarySearch(3, toIndex = -1)
        )
        assertEquals(
            INVALID,
            byteArray.binarySearch(3, toIndex = 100)
        )
        assertEquals(
            INVALID,
            byteArray.binarySearch(3, fromIndex = 4, toIndex = 2)
        )
    }

//--------------------------------------------------------------------------------------------------
//  minus
//--------------------------------------------------------------------------------------------------

    @Test
    fun minusValidIndex_intArray_returnsValidArray() {
        assertTrue(
            intArrayOf(1, 3, 5, 7, 12, 25/*, 100*/) contentEquals (intArray - 6)
        )
        assertTrue(
            intArrayOf(/*1, */3, 5, 7, 12, 25, 100) contentEquals (intArray - 0)
        )
        assertTrue(
            intArrayOf(1, 3, /*5,*/7, 12, 25, 100) contentEquals (intArray - 2)
        )
    }

    @Test
    fun minusInvalidIndex_intArray_returnsSameArray() {
        assertTrue(
            intArray contentEquals (intArray - (-1))
        )
        assertTrue(
            intArray contentEquals (intArray - 7)
        )
        assertTrue(
            intArray contentEquals (intArray - 100)
        )
    }

    @Test
    fun minusValidIndex_anyArray_returnsValidArray() {
        assertTrue(
            arrayOf("1", "three", "5", "seven", "12", "twenty five"/*, "100"*/) contentEquals (stringArray - 6)
        )
        assertTrue(
            arrayOf(/*"1", */"three", "5", "seven", "12", "twenty five", "100") contentEquals (stringArray - 0)
        )
        assertTrue(
            arrayOf("1", "three", /*"5", */"seven", "12", "twenty five", "100") contentEquals (stringArray - 2)
        )
    }

    @Test
    fun minusInvalidIndex_anyArray_returnsSameArray() {
        assertTrue(
            stringArray contentEquals (stringArray - (-1))
        )
        assertTrue(
            stringArray contentEquals (stringArray - 7)
        )
        assertTrue(
            stringArray contentEquals (stringArray - 100)
        )
    }

    @Test
    fun minusValidIndex_shortArray_returnsValidArray() {
        assertTrue(
            shortArrayOf(1, 3, 5, 7, 12, 25/*, 100*/) contentEquals (shortArray - 6)
        )
        assertTrue(
            shortArrayOf(/*1, */3, 5, 7, 12, 25, 100) contentEquals (shortArray - 0)
        )
        assertTrue(
            shortArrayOf(1, 3, /*5,*/7, 12, 25, 100) contentEquals (shortArray - 2)
        )
    }

    @Test
    fun minusInvalidIndex_shortArray_returnsSameArray() {
        assertTrue(
            shortArray contentEquals (shortArray - (-1))
        )
        assertTrue(
            shortArray contentEquals (shortArray - 7)
        )
        assertTrue(
            shortArray contentEquals (shortArray - 100)
        )
    }

    @Test
    fun minusValidIndex_byteArray_returnsValidArray() {
        assertTrue(
            byteArrayOf(1, 3, 5, 7, 12, 25/*, 100*/) contentEquals (byteArray - 6)
        )
        assertTrue(
            byteArrayOf(/*1, */3, 5, 7, 12, 25, 100) contentEquals (byteArray - 0)
        )
        assertTrue(
            byteArrayOf(1, 3, /*5,*/7, 12, 25, 100) contentEquals (byteArray - 2)
        )
    }

    @Test
    fun minusInvalidIndex_byteArray_returnsSameArray() {
        assertTrue(
            byteArray contentEquals (byteArray - (-1))
        )
        assertTrue(
            byteArray contentEquals (byteArray - 7)
        )
        assertTrue(
            byteArray contentEquals (byteArray - 100)
        )
    }
}