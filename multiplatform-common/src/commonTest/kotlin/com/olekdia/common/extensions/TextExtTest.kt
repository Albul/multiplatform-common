package com.olekdia.common.extensions

import kotlin.test.Test
import kotlin.test.assertEquals

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
}