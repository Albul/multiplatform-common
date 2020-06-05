package com.olekdia.common.extensions

import kotlin.math.abs
import kotlin.test.*

class ColorExtTest {

    fun assertEquals(expected: Float, actual: Float, delta: Float) {
        assertTrue(abs(actual - expected) <= delta)
    }

    @Test
    fun alphaTest() {
        assertEquals(0xAB, 0xAB342321.toInt().alpha)
        assertEquals(0x12, 0x12342321.alpha)
        assertEquals(0x02, 0x02342321.alpha)
        assertEquals(0x11, 0x11342321.alpha)
        assertEquals(0, 0x00342321.alpha)
    }

    @Test
    fun redTest() {
        assertEquals(0x34, 0x11342321.red)
        assertEquals(0x11, 0x12112321.red)
        assertEquals(0x01, 0x02012321.red)
        assertEquals(0, 0x11002321.red)
        assertEquals(0xFF, 0x00FF2321.red)
    }

    @Test
    fun greenTest() {
        assertEquals(0x23, 0x11342321.green)
        assertEquals(0x11, 0x12231121.green)
        assertEquals(0x01, 0x02340121.green)
        assertEquals(0, 0x11550021.green)
        assertEquals(0xFF, 0x0021FF21.green)
        assertEquals(0xFF, 0x0000FF00.green)
    }

    @Test
    fun blueTest() {
        assertEquals(0x23, 0x11348823.blue)
        assertEquals(0x11, 0x12238811.blue)
        assertEquals(0x01, 0x02348801.blue)
        assertEquals(0, 0x11558800.blue)
        assertEquals(0xFF, 0x002188FF.blue)
        assertEquals(0xFF, 0x000000FF.blue)
    }

    @Test
    fun hueTest() {
        val delta = 0.0001F
        assertEquals(6.315796F, 0x11342321.hue, delta)
        assertEquals(127.05882F, 0x00119921.hue, delta)
        assertEquals(352.37286F, 0x00881221.hue, delta)
        assertEquals(246.76692F, 0x00211297.hue, delta)

        assertEquals(0.0F, 0x00650000.hue, delta)
        assertEquals(120.0F, 0x00008500.hue, delta)
        assertEquals(240.0F, 0x00000085.hue, delta)
        assertEquals(300.0F, 0x11850085.hue, delta)
        assertEquals(0.0F, 0.hue, delta)
        assertEquals(0.0F, 0xFFFFFFFF.toInt().hue, delta)
        assertEquals(60.0F, 0xFFFFFF25.toInt().hue, delta)
    }

    @Test
    fun saturationTest() {
        val delta = 0.0001F
        assertEquals(0.3653846F, 0x11342321.saturation, delta)
        assertEquals(0.8888889F, 0x00119921.saturation, delta)
        assertEquals(0.86764705F, 0x00881221.saturation, delta)
        assertEquals(0.8807947F, 0x00211297.saturation, delta)

        assertEquals(1.0F, 0x00650000.saturation, delta)
        assertEquals(1.0F, 0x00008500.saturation, delta)
        assertEquals(1.0F, 0x00000085.saturation, delta)
        assertEquals(1.0F, 0x11850085.saturation, delta)
        assertEquals(0.0F, 0.saturation, delta)
        assertEquals(0.0F, 0xFFFFFFFF.toInt().saturation, delta)
        assertEquals(0.85490197F, 0xFFFFFF25.toInt().saturation, delta)
    }

    @Test
    fun brightnessTest() {
        val delta = 0.0001F
        assertEquals(0.20392157F, 0x11342321.brightness, delta)
        assertEquals(0.6F, 0x00119921.brightness, delta)
        assertEquals(0.53333336F, 0x00881221.brightness, delta)
        assertEquals(0.5921569F, 0x00211297.brightness, delta)

        assertEquals(0.39607844F, 0x00650000.brightness, delta)
        assertEquals(0.52156866F, 0x00008500.brightness, delta)
        assertEquals(0.52156866F, 0x00000085.brightness, delta)
        assertEquals(0.52156866F, 0x11850085.brightness, delta)
        assertEquals(0.0F, 0.brightness, delta)
        assertEquals(1.0F, 0xFFFFFFFF.toInt().brightness, delta)
        assertEquals(1.0F, 0xFFFFFF25.toInt().brightness, delta)
    }

    @Test
    fun rgbOfIntsTest() {
        assertEquals(0xFF342321.toInt(), rgb(0x34, 0x23, 0x21))
        assertEquals(0xFF550001.toInt(), rgb(0x55, 0x00, 0x01))
        assertEquals(0xFF55FF01.toInt(), rgb(0x55, 0xFF, 0x01))
        assertEquals(0xFF0000FF.toInt(), rgb(0x00, 0x00, 0xFF))
        assertEquals(0xFFFF0000.toInt(), rgb(0xFF, 0x00, 0x00))
        assertEquals(0xFF000000.toInt(), rgb(0, 0, 0))
    }

    @Test
    fun getRandomColorTest() {
        val set = mutableSetOf<Int>()

        for (i in 1..100) {
            set.add(getRandomColor())
        }
        assertEquals(100, set.size) // All unique
    }

    @Test
    fun toOpaqueColorTest() {
        assertEquals(0xFF342321.toInt(), 0x12342321.toOpaqueColor())
        assertEquals(0xFF00FF00.toInt(), 0x2300FF00.toOpaqueColor())
        assertEquals(0xFF0000FF.toInt(), 0x310000FF.toOpaqueColor())
        assertEquals(0xFFFF0000.toInt(), 0x86FF0000.toInt().toOpaqueColor())
    }

    @Test
    fun isDarkColor() {
        assertTrue(0xFF990000.toInt().isDarkColor())
        assertTrue(0xFF808000.toInt().isDarkColor())
        assertTrue(0xFF556B2F.toInt().isDarkColor())
        assertTrue(0xFF006400.toInt().isDarkColor())
        assertTrue(0xFF4682B4.toInt().isDarkColor())
        assertTrue(0xFF191970.toInt().isDarkColor())
        assertTrue(0xFF483D8B.toInt().isDarkColor())
        assertTrue(0xFF8B4513.toInt().isDarkColor())
        assertTrue(0xFF228B22.toInt().isDarkColor())
        assertTrue(0xFF696969.toInt().isDarkColor())
        assertTrue(0xFF708090.toInt().isDarkColor())


        assertFalse(0xFFFFFF00.toInt().isDarkColor())
        assertFalse(0xFFF0E68C.toInt().isDarkColor())
        assertFalse(0xFFADFF2F.toInt().isDarkColor())
        assertFalse(0xFF98FB98.toInt().isDarkColor())
        assertFalse(0xFF20B2AA.toInt().isDarkColor())
        assertFalse(0xFF00CED1.toInt().isDarkColor())
        assertFalse(0xFFF5DEB3.toInt().isDarkColor())
        assertFalse(0xFFB0C4DE.toInt().isDarkColor())
        assertFalse(0xFFC0C0C0.toInt().isDarkColor())
        assertFalse(0xFFF0FFFF.toInt().isDarkColor())
    }

    @Test
    fun withAlphaTest() {
        assertEquals(0xAB00FF00.toInt(), 0xFF00FF00.toInt().withAlpha(0xAB))
        assertEquals(0xCD12FF25.toInt(), 0x2412FF25.toInt().withAlpha(0xCD))
    }

    @Test
    fun toArgbStringTest() {
        assertEquals("#AB00FF00", 0xAB00FF00.toInt().toArgbString())
        assertEquals("#FF01FF94", 0xFF01FF94.toInt().toArgbString())
        assertEquals("#1201FF00", 0x1201FF00.toInt().toArgbString())
        assertEquals("#0001FF00", 0x0001FF00.toInt().toArgbString())
        assertEquals("#0000FF00", 0x0000FF00.toInt().toArgbString())
        assertEquals("#FF000000", 0xFF000000.toInt().toArgbString())
        assertEquals("#FF000001", 0xFF000001.toInt().toArgbString())
        assertEquals("#00000001", 0x00000001.toInt().toArgbString())
        assertEquals("#00000000", 0.toArgbString())
        assertEquals("#00100010", 0x00100010.toArgbString())
    }

    @Test
    fun toRgbStringTest() {
        assertEquals("#00FF00", 0xAB00FF00.toInt().toRgbString())
        assertEquals("#01FF94", 0xFF01FF94.toInt().toRgbString())
        assertEquals("#01FF00", 0x1201FF00.toInt().toRgbString())
        assertEquals("#01FF00", 0x0001FF00.toInt().toRgbString())
        assertEquals("#00FF00", 0x0000FF00.toInt().toRgbString())
        assertEquals("#000000", 0xFF000000.toInt().toRgbString())
        assertEquals("#000001", 0xFF000001.toInt().toRgbString())
        assertEquals("#000001", 0x00000001.toInt().toRgbString())
        assertEquals("#000000", 0.toRgbString())
        assertEquals("#100010", 0x00100010.toRgbString())
    }
}