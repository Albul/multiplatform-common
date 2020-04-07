package com.olekdia.common.extensions

import kotlin.test.*

class BitExtTest {

    @Test
    fun toBitIndex() {
        assertEquals(5, 0b100000.toBitIndex())
        assertEquals(2, 0b100.toBitIndex())
        assertEquals(1, 0b10.toBitIndex())
        assertEquals(0, 0b1.toBitIndex())
    }

    @Test
    fun isBitEnabled() {
        assertTrue(0b1_010_100.isBitEnabled(0b1_000_000))
        assertFalse(0b1_010_100.isBitEnabled(0b100_000))
        assertTrue(0b1_010_100.isBitEnabled(0b10_000))
        assertTrue(0b1_010_100.isBitEnabled(0b100))
        assertFalse(0b1_010_100.isBitEnabled(0b10))
        assertTrue(0b1_010_100.isBitEnabled(-0b100))
    }

    @Test
    fun isBitIndexEnabled() {
        assertTrue(0b1_010_100.isBitIndexEnabled(6))
        assertFalse(0b1_010_100.isBitIndexEnabled(5))
        assertTrue(0b1_010_100.isBitIndexEnabled(4))
        assertTrue(0b1_010_100.isBitIndexEnabled(2))
        assertFalse(0b1_010_100.isBitIndexEnabled(1))
        assertFalse(0b1_010_100.isBitIndexEnabled(0))
        assertTrue(0b1_010_101.isBitIndexEnabled(0))
    }

    @Test
    fun addBit() {
        assertEquals(0b1_010_110, 0b1_010_100.addBit(0b10))
        assertNotEquals(0b1_010_111, 0b1_010_100.addBit(0b10))
        assertEquals(0b1_010_111, 0b1_010_100.addBit(0b111))
        assertEquals(0b11_010_100, 0b1_010_100.addBit(0b11_000_000))
        assertEquals(0b11_010_100, 0b1_010_100.addBit(0b10_000_000))
        assertEquals(0b0_010_100, 0b0_010_100.addBit(0b10_100))
        assertEquals(0b0_011_111, 0b0_010_100.addBit(0b11_111))
    }

    @Test
    fun addBitLong() {
        assertEquals(0b1_010_110L, 0b1_010_100L.addBit(0b10L))
        assertNotEquals(0b1_010_111L, 0b1_010_100L.addBit(0b10L))
        assertEquals(0b1_010_111L, 0b1_010_100L.addBit(0b111L))
        assertEquals(0b11_010_100L, 0b1_010_100L.addBit(0b11_000_000L))
        assertEquals(0b11_010_100L, 0b1_010_100L.addBit(0b10_000_000L))
        assertEquals(0b0_010_100L, 0b0_010_100L.addBit(0b10_100L))
        assertEquals(0b0_011_111L, 0b0_010_100L.addBit(0b11_111L))
    }

    @Test
    fun addBitIndex() {
        assertEquals(0b1_010_110, 0b1_010_100.addBitIndex(1))
        assertEquals(0b1_010_110, 0b1_010_110.addBitIndex(1)) // Was added before
        assertNotEquals(0b1_010_111, 0b1_010_100.addBitIndex(1))
        assertEquals(0b1_010_101, 0b1_010_100.addBitIndex(0))
        assertEquals(0b11_010_100, 0b1_010_100.addBitIndex(7))
        assertNotEquals(0b11_010_100, 0b1_010_100.addBitIndex(6))
        assertEquals(0b0_011_111, 0b0_010_100.addBitIndex(0).addBitIndex(1).addBitIndex(3))
    }

    @Test
    fun addBitIndexLong() {
        assertEquals(0b1_010_110L, 0b1_010_100L.addBitIndex(1))
        assertEquals(0b1_010_110L, 0b1_010_110L.addBitIndex(1)) // Was added before
        assertEquals(0b1_010_101L, 0b1_010_100L.addBitIndex(0))
        assertEquals(0b0_011_111L, 0b0_010_100L.addBitIndex(0).addBitIndex(1).addBitIndex(3))
    }

    @Test
    fun subtractBit() {
        assertEquals(0b1_010_000, 0b1_010_100.subtractBit(0b100))
        assertEquals(0b1_000_001L, 0b1_010_101L.subtractBit(0b10_100))
        assertEquals(0b0_010_001, 0b0_011_111.subtractBit(0b100).subtractBit(0b110).subtractBit(0b1_000))
    }

    @Test
    fun subtractBitIndex() {
        assertEquals(0b1_010_100, 0b1_010_110.subtractBitIndex(1))
        assertEquals(0b1_010_100, 0b1_010_100.subtractBitIndex(1)) // Was subtracted before
        assertEquals(0b1_010_100, 0b1_010_101.subtractBitIndex(0))
        assertEquals(0b0_011_100, 0b0_011_111.subtractBitIndex(0).subtractBitIndex(1))
    }

    @Test
    fun addBitIndexes() {
        assertEquals(0b1_111_110, 0b1_010_100.addBitIndexes(1, 2, 3, 5))
        assertEquals(0b100_101, 0.addBitIndexes(0, 2, 5))
        assertEquals(0b10, 0.addBitIndexes(1))
        assertEquals(1, 0.addBitIndexes(0))
    }

    @Test
    fun subtractBitIndexes() {
        assertEquals(0b1_010_000, 0b1_111_110.subtractBitIndexes(1, 2, 3, 5))
        assertEquals(0, 0.subtractBitIndexes(0, 2, 5))
    }
}