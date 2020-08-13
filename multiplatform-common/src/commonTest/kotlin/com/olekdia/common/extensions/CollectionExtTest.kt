package com.olekdia.common.extensions

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CollectionExtTest {

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

    @Test
    fun contentEquals_ofSet_returnsTrue() {
        assertTrue(
            setOf(1, 2, 3, 4) contentEquals setOf(4, 3, 1, 2)
        )
        assertTrue(
            setOf(1, 2, 3, 4) contentEquals setOf(4, 3, 1, 2, 1, 4)
        )
        assertTrue(
            setOf(" ", "12", "#$", "", "#$%^", "", " ") contentEquals setOf("", " ", "#$", "#$%^", "12",)
        )
        assertTrue(
            setOf(0.342423f, 0.12321312f, 0.9887888f) contentEquals mutableSetOf(0.9887888f, 0.12321312f, 0.342423f)
        )
        assertTrue(
            hashSetOf(0.342423f, 0.12321312f, 0.9887888f) contentEquals setOf(0.9887888f, 0.12321312f, 0.342423f)
        )
        assertTrue(
            hashSetOf<Char>() contentEquals setOf<Char>()
        )
        // Any empty set has equaled content to any other typed empty set
        assertTrue(
            hashSetOf<Char>() contentEquals setOf<Int>()
        )
    }

    @Test
    fun contentEquals_ofSet_returnsFalse() {
        assertFalse(
            setOf(1, 2, 3) contentEquals setOf(4, 3, 1, 2)
        )
        assertFalse(
            setOf(1, 2, 3, 4) contentEquals setOf(4, 3, 1)
        )
        assertFalse(
            setOf(1, 2) contentEquals setOf(1, 3)
        )
        assertFalse(
            setOf(" ", "12", "#$", "", "#$%^", "", " ") contentEquals setOf("", " ", "#$%^", "12",)
        )
        assertFalse(
            setOf(0.342423f, 0.12321312f) contentEquals mutableSetOf(0.9887888f, 0.12321312f, 0.342423f)
        )
        assertFalse(
            hashSetOf(0.342423f, 0.12321312f, 0f) contentEquals setOf(0.12321312f, 0.342423f)
        )
    }
}