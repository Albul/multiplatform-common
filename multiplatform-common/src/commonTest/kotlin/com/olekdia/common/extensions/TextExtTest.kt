package com.olekdia.common.extensions

import com.olekdia.common.EQUALS
import com.olekdia.common.FIRST_GREATER
import com.olekdia.common.SECOND_GREATER
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TextExtTest {

    @Test
    fun appendNull() {
        val b = StringBuilder()
        b.append("New")
            .appendNull()

        assertEquals("Newnull", b.toString())
    }

    @Test
    fun appendSpaces() {
        val b = StringBuilder()
        b.append("New")
            .appendSpaces(5)

        assertEquals("New     ", b.toString())

        b.clear()
        b.append("New")
            .appendSpaces(0)
        assertEquals("New", b.toString())
    }

    @Test
    fun reuse() {
        val b = StringBuilder()
        b.append("New")

        assertEquals("New", b.toString())

        assertEquals(
            "Oldnull",
            b.reuse {
                it.append("Old")
                    .appendNull()
                it.toString()
            }
        )
    }

    @Test
    fun equalsNullable() {
        assertTrue(
            "New".equalsNullable("new", ignoreCase = true)
        )
        assertTrue(
            "".equalsNullable(null, ignoreCase = true)
        )
        assertTrue(
            null.equalsNullable("", ignoreCase = true)
        )
        assertTrue(
            "".equalsNullable("", ignoreCase = true)
        )
        assertTrue(
            null.equalsNullable(null, ignoreCase = true)
        )
        assertFalse(
            "New".equalsNullable("", ignoreCase = true)
        )
        assertFalse(
            "New".equalsNullable(null, ignoreCase = true)
        )
        assertFalse(
            "".equalsNullable("New", ignoreCase = true)
        )
        assertFalse(
            "New".equalsNullable("Nev", ignoreCase = true)
        )
    }

    @Test
    fun string_equalsIgnoreCase_nullAndEmptyCases() {
        assertEquals(true, null.equalsNullable(null, true))
        assertEquals(true, null.equalsNullable(null, false))
        assertEquals(true, "".equalsNullable("", true))
        assertEquals(true, "".equalsNullable("", false))
        assertEquals(true, null.equalsNullable("", true))
        assertEquals(true, null.equalsNullable("", false))
        assertEquals(true, "".equalsNullable(null, true))
        assertEquals(true, "".equalsNullable(null, false))
    }

    @Test
    fun string_equalsIgnoreCase_sameStringCases() {
        assertEquals(true, "Kiev".equalsNullable("kIEv", true))
        assertEquals(false, "Kiev".equalsNullable("kIEv", false))
        assertEquals(true, "Kiev".equalsNullable("Kiev", true))
        assertEquals(true, "Kiev".equalsNullable("Kiev", false))
        assertEquals(true, "kiev".equalsNullable("kiev", true))
        assertEquals(true, "kiev".equalsNullable("kiev", false))
    }

    @Test
    fun equalsNullable_ignoreCaseFalse() {
        assertFalse(
            "New".equalsNullable("new", ignoreCase = false)
        )
        assertTrue(
            "".equalsNullable(null, ignoreCase = false)
        )
        assertTrue(
            null.equalsNullable("", ignoreCase = false)
        )
        assertTrue(
            "".equalsNullable("", ignoreCase = false)
        )
        assertTrue(
            null.equalsNullable(null, ignoreCase = false)
        )
        assertFalse(
            "New".equalsNullable("", ignoreCase = false)
        )
        assertFalse(
            "New".equalsNullable(null, ignoreCase = false)
        )
        assertFalse(
            "".equalsNullable("New", ignoreCase = false)
        )
        assertFalse(
            "New".equalsNullable("Nev", ignoreCase = false)
        )
    }

    @Test
    fun containsNullable_ignoreCaseTrue() {
        val str = "We're happy to announce that Kotlin scripting support will be available in Gradle 3.0"
        assertTrue(str.containsNullable("HaPPy", ignoreCase = true))

        assertTrue(str.containsNullable("", ignoreCase = true))
        assertFalse(str.containsNullable(null, ignoreCase = true))
        assertTrue(str.containsNullable("WILL", ignoreCase = true))
        assertTrue(str.containsNullable("Gradle", ignoreCase = true))
        assertTrue(str.containsNullable("3", ignoreCase = true))

        assertFalse("".containsNullable(str, ignoreCase = true))
        assertFalse("".containsNullable(null, ignoreCase = true))
        assertFalse("".containsNullable("Kotlin", ignoreCase = true))
        assertTrue("".containsNullable("", ignoreCase = true))

        assertFalse(null.containsNullable("", ignoreCase = true))
        assertFalse(null.containsNullable(null, ignoreCase = true))
        assertFalse(null.containsNullable("Kotlin", ignoreCase = true))
    }

    @Test
    fun containsNullable_ignoreCaseFalse() {
        val str = "We're happy to announce that Kotlin scripting support will be available in Gradle 3.0"
        assertFalse(str.containsNullable("HaPPy", ignoreCase = false))
        assertTrue(str.containsNullable("happy", ignoreCase = false))

        assertTrue(str.containsNullable("", ignoreCase = false))
        assertFalse(str.containsNullable(null, ignoreCase = false))
        assertFalse(str.containsNullable("WILL", ignoreCase = false))
        assertTrue(str.containsNullable("Gradle", ignoreCase = false))
        assertTrue(str.containsNullable("3", ignoreCase = false))

        assertFalse("".containsNullable(str, ignoreCase = false))
        assertFalse("".containsNullable(null, ignoreCase = false))
        assertFalse("".containsNullable("Kotlin", ignoreCase = false))
        assertTrue("".containsNullable("", ignoreCase = false))

        assertFalse(null.containsNullable("", ignoreCase = false))
        assertFalse(null.containsNullable(null, ignoreCase = false))
        assertFalse(null.containsNullable("Kotlin", ignoreCase = false))
    }

    @Test
    fun compareToNullable_ignoreCaseTrue() {
        assertEquals(EQUALS, "a".compareToNullable("A", ignoreCase = true))
        assertEquals(EQUALS, "".compareToNullable("", ignoreCase = true))
        assertEquals(EQUALS, null.compareToNullable(null, ignoreCase = true))

        assertEquals(EQUALS, null.compareToNullable("", ignoreCase = true))
        assertEquals(EQUALS, "".compareToNullable(null, ignoreCase = true))

        assertEquals(EQUALS, "A".compareToNullable("a", ignoreCase = true))
        assertEquals(SECOND_GREATER, "A".compareToNullable("B", ignoreCase = true))
        assertEquals(FIRST_GREATER, "B".compareToNullable("A", ignoreCase = true))
    }

    @Test
    fun compareToNullable_ignoreCaseFalse() {
        assertTrue("a".compareToNullable("A", ignoreCase = false) > 0)
        assertEquals(EQUALS, "".compareToNullable("", ignoreCase = false))
        assertEquals(EQUALS, null.compareToNullable(null, ignoreCase = false))

        assertEquals(EQUALS, null.compareToNullable("", ignoreCase = false))
        assertEquals(EQUALS, "".compareToNullable(null, ignoreCase = false))

        assertTrue("A".compareToNullable("a", ignoreCase = false) < 0)
        assertTrue("A".compareToNullable("B", ignoreCase = false) < 0)
        assertTrue("B".compareToNullable("A", ignoreCase = false) > 0)
    }

    @Test
    fun trimBidi() {
        assertEquals(
            "24:00", "\u206624\u2069:\u206600\u2069".trimBidi()
        )
        assertEquals(
            "24:00", "24\u2066\u2069:\u2066\u206900".trimBidi()
        )
        assertEquals(
            "24:00", "24:00".trimBidi()
        )
        assertEquals(
            "24 : 00", "\u206624\u2069:\u206600\u2069".trimBidi(" ")
        )
        assertEquals(
            "24  :  00", "24\u2066\u2069:\u2066\u206900".trimBidi(" ")
        )
        assertEquals(
            "24:00", "24:00".trimBidi(" ")
        )
    }
}