package com.olekdia.common.extensions

import kotlin.test.*

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
    }
}