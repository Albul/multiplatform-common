package com.olekdia.common.extensions

import com.olekdia.common.EQUALS
import com.olekdia.common.FIRST_GREATER
import com.olekdia.common.INVALID
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
    fun removeLast() {
        val b = StringBuilder()
        b.append("New!")
            .removeLast()
            .append('.')

        assertEquals("New.", b.toString())

        b.removeLast()
        assertEquals("New", b.toString())


        b.removeLast()
        assertEquals("Ne", b.toString())

        b.removeLast()
        assertEquals("N", b.toString())

        b.removeLast()
        assertEquals("", b.toString())

        b.removeLast()
        assertEquals("", b.toString())

        b.removeLast()
        assertEquals("", b.toString())
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
    fun joinToString() {
        assertEquals(
            "1,2,3,4,5",
            listOf<String>(
                "1", "2", "3", "4", "5"
            ).joinToString(',')
        )

        assertEquals(
            "a:b:c:d:e",
            listOf<String>(
                "a", "b", "c", "d", "e"
            ).joinToString(':')
        )

        assertEquals(
            "123 456 789",
            listOf<String>(
                "123", "456", "789"
            ).joinToString(' ')
        )

        assertEquals(
            "  ",
            listOf<String>(
                "", "", ""
            ).joinToString(' ')
        )
    }

    @Test
    fun toIntOrZero() {
        assertEquals(1, "1".toIntOrZero())
        assertEquals(-1, "-1".toIntOrZero())
        assertEquals(1, "+1".toIntOrZero())
        assertEquals(1123, "+1123".toIntOrZero())
        assertEquals(0, "+0".toIntOrZero())
        assertEquals(0, "-0".toIntOrZero())
        assertEquals(0, "0".toIntOrZero())
        assertEquals(2595, "2595".toIntOrZero())
        assertEquals(-2595, "-2595".toIntOrZero())
        assertEquals(2595, "+2595".toIntOrZero())
        assertEquals(Int.MAX_VALUE, "2147483647".toIntOrZero())
        assertEquals(Int.MIN_VALUE, "-2147483648".toIntOrZero())
        assertEquals(0, "-21474-83648".toIntOrZero())
        assertEquals(0, "-21474A83648".toIntOrZero())
        assertEquals(0, "-2147D".toIntOrZero())
        assertEquals(0, "$".toIntOrZero())
        assertEquals(0, "".toIntOrZero())
        assertEquals(0, "   ".toIntOrZero())
        assertEquals(0, null.toIntOrZero())
    }

    @Test
    fun toIntOr() {
        assertEquals(1, "1".toIntOr(INVALID))
        assertEquals(-1, "-1".toIntOr(INVALID))
        assertEquals(1, "+1".toIntOr(INVALID))
        assertEquals(1123, "+1123".toIntOr(INVALID))
        assertEquals(0, "+0".toIntOr(INVALID))
        assertEquals(0, "-0".toIntOr(INVALID))
        assertEquals(0, "0".toIntOr(INVALID))
        assertEquals(2595, "2595".toIntOr(INVALID))
        assertEquals(-2595, "-2595".toIntOr(INVALID))
        assertEquals(2595, "+2595".toIntOr(INVALID))
        assertEquals(Int.MAX_VALUE, "2147483647".toIntOr(INVALID))
        assertEquals(Int.MIN_VALUE, "-2147483648".toIntOr(INVALID))
        assertEquals(INVALID, "-21474-83648".toIntOr(INVALID))
        assertEquals(INVALID, "-21474A83648".toIntOr(INVALID))
        assertEquals(INVALID, "-2147D".toIntOr(INVALID))
        assertEquals(INVALID, "$".toIntOr(INVALID))
        assertEquals(325, "".toIntOr(325))
        assertEquals(325, "   ".toIntOr(325))
        assertEquals(325, null.toIntOr(325))
    }

    @Test
    fun toSet() {
        assertTrue(
            setOf<String>(
                "1", "2", "3", "4", "5",
            ).contentEquals(
                "1,2,3,4,5".toSet(",")
            )
        )

        assertTrue(
            setOf<String>(
                "a", "b", "c", "d"
            ).contentEquals(
                "a b c d".toSet(" ")
            )
        )
        assertTrue(
            setOf<String>(
                "abd", "cde", "fgh"
            ).contentEquals(
                "abd-*|*-cde-*|*-fgh".toSet("-*|*-")
            )
        )
        assertTrue(
            setOf<String>().contentEquals(
                "   ".toSet(" ")
            )
        )
        assertTrue(
            setOf<String>().contentEquals(
                " , ,  ,    ,".toSet()
            )
        )
        assertTrue(
            setOf<String>(" ", "  ", "    ").contentEquals(
                " ,  ,    ,".toSet(delimiter = ",", trimWhitespaces = false)
            )
        )
        assertTrue(
            setOf<String>("a", "b", "c").contentEquals(
                "a  b  c   ".toSet(" ")
            )
        )
        assertTrue(
            setOf<String>("a", "b", "c").contentEquals(
                "a b a a a c c    ".toSet(" ")
            )
        )
        assertTrue(
            setOf<String>().contentEquals(
                "".toSet(" ")
            )
        )
        assertTrue(
            setOf<String>().contentEquals(
                "".toSet("|")
            )
        )
        assertTrue(
            setOf<String>().contentEquals(
                "".toSet("")
            )
        )
        assertTrue(
            setOf<String>("a", "b", "c").contentEquals(
                "abc".toSet("")
            )
        )
    }

    @Test
    fun ellipsize() {
        assertEquals("U pop…", "U popa bula sobaka".ellipsize(6))
        assertEquals("", "U popa bula".ellipsize(0))
        assertEquals("…", "U popa bula".ellipsize(1))
        assertEquals("U…", "U popa bula".ellipsize(2))
        assertEquals("U p\u0000", "U popa bula".ellipsize(4, ending = '\u0000'))
        assertEquals("\u0000", "U popa bula".ellipsize(1, ending = '\u0000'))
        assertEquals("", "U popa bula".ellipsize(0, ending = '\u0000'))

        assertEquals("U p", "U popa bula".ellipsize(4, ending = null))
        assertEquals("", "U popa bula".ellipsize(1, ending = null))
        assertEquals("", "U popa bula".ellipsize(0, ending = null))
        assertEquals("U popa bula sobaka", "U popa bula sobaka".ellipsize(Int.MAX_VALUE))
        assertEquals("U popa bula sobaka", "U popa bula sobaka".ellipsize(18))
        assertEquals("U popa bula soba…", "U popa bula sobaka".ellipsize(17))
        assertEquals("", "U popa bula sobaka".ellipsize(-2147483640))
        assertEquals("", "U popa bula sobaka".ellipsize(Int.MIN_VALUE))

        assertEquals("", "".ellipsize(10))
        assertEquals("", "".ellipsize(0))
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


        val str2 = "<![CDATA[\n25\n\n325,333\n\n\n]]>We're happy to announce that Kotlin scripting support will be available in Gradle 3.0"
        val cdataOffset = str2.indexOf("]]>") + "]]>".length

        assertTrue(str2.containsNullable("Gradle", ignoreCase = true))
        assertTrue(str2.containsNullable("Gradle", ignoreCase = true, offset = cdataOffset))
        assertTrue(str2.containsNullable("CDATA", ignoreCase = true))
        assertFalse(str2.containsNullable("CDATA", ignoreCase = true, offset = cdataOffset))

        assertTrue(str2.containsNullable("333", ignoreCase = true))
        assertFalse(str2.containsNullable("333", ignoreCase = true, offset = cdataOffset))

        assertTrue(str2.containsNullable("25", ignoreCase = true))
        assertFalse(str2.containsNullable("25", ignoreCase = true, offset = cdataOffset))

        assertTrue(str2.containsNullable("]]", ignoreCase = true))
        assertFalse(str2.containsNullable("]]", ignoreCase = true, offset = cdataOffset))

        assertTrue(str2.containsNullable("]]>", ignoreCase = true))
        assertFalse(str2.containsNullable("]]>", ignoreCase = true, offset = cdataOffset))

        assertTrue(str2.containsNullable(">", ignoreCase = true))
        assertFalse(str2.containsNullable(">", ignoreCase = true, offset = cdataOffset))

        assertTrue(str2.containsNullable("We", ignoreCase = true))
        assertTrue(str2.containsNullable("We", ignoreCase = true, offset = cdataOffset))

        assertTrue(str2.containsNullable("We're happy", ignoreCase = true))
        assertTrue(str2.containsNullable("We're happy", ignoreCase = true, offset = cdataOffset))
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

        assertEquals(
            "24 : 00", "\u202D24\u2069:\u202E00\u2069".trimBidi(" ")
        )
    }

    @Test
    fun toSingleLine() {
        assertEquals(
            "Nizhyn is the capital of Ukraine!",
            "Nizhyn\nis the capital\n\rof Ukraine!".toSingleLine()
        )

        assertEquals(
            "Nizhyn-|-is the capital-|-of-|-Ukraine!",
            "Nizhyn\nis the capital\n\rof\t\tUkraine!".toSingleLine("-|-")
        )

        assertEquals(
            "Nizhyn is the capital of Ukraine!",
            "Nizhyn\n\n\n\n\n\nis the capital\n\r\n\r\n\rof Ukraine!".toSingleLine()
        )

        assertEquals(
            "Nizhyn is the capital of Ukraine! ",
            "Nizhyn is the capital of Ukraine!\n".toSingleLine()
        )
    }

    @Test
    fun countPrintable() {
        assertEquals(3, "fda".countPrintable())
        assertEquals(0, "".countPrintable())
        assertEquals(0, null.countPrintable())
        assertEquals(1, " ".countPrintable())
        assertEquals(2, "1読\n".countPrintable())
        assertEquals(11, "У попа була\n\u0085\u200E".countPrintable())
    }

    // https://www.loginradius.com/engineering/blog/eol-end-of-line-or-newline-characters/#:~:text=This%20character%20is%20commonly%20known,known%20as%20'Carriage%20Return'.
    @Test
    fun countLines() {
        assertEquals(0, "".countLines())
        assertEquals(0, null.countLines())
        assertEquals(1, "12".countLines())
        assertEquals(1, "Bla bla bla bla".countLines())

        assertEquals(3, "Bla\n32\nBla".countLines())
        assertEquals(9, "Bla\n32\nBla\n\n\n\n\n\n".countLines())
        assertEquals(2, "Bla\n".countLines())
        assertEquals(2, "Bla\nBla".countLines())
        assertEquals(2, "\nBlaBla".countLines())

        assertEquals(3, "Bla\r32\rBla".countLines())
        assertEquals(9, "Bla\r32\rBla\r\r\r\r\r\r".countLines())
        assertEquals(2, "Bla\r".countLines())
        assertEquals(2, "Bla\rBla".countLines())
        assertEquals(2, "\rBlaBla".countLines())

        assertEquals(3, "Bla\r\n32\r\nBla".countLines())
        assertEquals(9, "Bla\r\n32\r\nBla\r\n\r\n\r\n\r\n\r\n\r\n".countLines())
        assertEquals(2, "Bla\r\n".countLines())
        assertEquals(2, "Bla\r\nBla".countLines())
        assertEquals(2, "\r\nBlaBla".countLines())
    }

    @Test
    fun isPrintable() {
        assertTrue(
            'a'.isPrintable()
        )
        assertTrue(
            ' '.isPrintable()
        )
        assertTrue(
            '読'.isPrintable()
        )
        assertTrue(
            '1'.isPrintable()
        )
        assertTrue(
            '%'.isPrintable()
        )
        assertTrue(
            '№'.isPrintable()
        )

        assertFalse(
            '\u202E'.isPrintable() // bidi
        )
        assertFalse(
            '\u2068'.isPrintable() // bidi
        )
        assertFalse(
            '\u202B'.isPrintable() // bidi
        )
        assertFalse(
            '\u200E'.isPrintable() // bidi
        )
        assertFalse(
            '\uD800'.isPrintable() // surrogate
        )
        assertFalse(
            '\uDB7F'.isPrintable() // surrogate
        )
        assertFalse(
            '\uDC00'.isPrintable() // surrogate
        )
        assertFalse(
            '\u007F'.isPrintable() // control
        )
        assertFalse(
            '\u001F'.isPrintable() // control
        )
        assertFalse(
            '\u0000'.isPrintable() // control
        )
        assertFalse(
            '\n'.isPrintable() // control
        )
        assertFalse(
            '\r'.isPrintable() // control
        )
        assertFalse(
            '\t'.isPrintable() // control
        )
        assertFalse(
            '\u0085'.isPrintable() // control
        )
    }

    @Test
    fun isISOControl() {
        assertFalse(
            'a'.isISOControl()
        )
        assertFalse(
            ' '.isISOControl()
        )
        assertFalse(
            '読'.isISOControl()
        )
        assertFalse(
            '1'.isISOControl()
        )
        assertFalse(
            '%'.isISOControl()
        )
        assertFalse(
            '№'.isISOControl()
        )

        assertFalse(
            '\u202E'.isISOControl() // bidi
        )
        assertFalse(
            '\u2068'.isISOControl() // bidi
        )
        assertFalse(
            '\u202B'.isISOControl() // bidi
        )
        assertFalse(
            '\u200E'.isISOControl() // bidi
        )
        assertFalse(
            '\uD800'.isISOControl() // surrogate
        )
        assertFalse(
            '\uDB7F'.isISOControl() // surrogate
        )
        assertFalse(
            '\uDC00'.isISOControl() // surrogate
        )
        assertTrue(
            '\u007F'.isISOControl() // control
        )
        assertTrue(
            '\u001F'.isISOControl() // control
        )
        assertTrue(
            '\u0000'.isISOControl() // control
        )
        assertTrue(
            '\n'.isISOControl() // control
        )
        assertTrue(
            '\r'.isISOControl() // control
        )
        assertTrue(
            '\t'.isISOControl() // control
        )
        assertTrue(
            '\u0085'.isISOControl() // control
        )
    }
}