package com.olekdia.common.extensions

import com.olekdia.common.NumeralSystem
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocaleExtTest {

    @Test
    fun StringBuilder_toIsoNumerals() {
        assertEquals(
            "0",
            StringBuilder("٠")
                .toIsoNumerals(NumeralSystem.EASTERN_ARABIC)
                .toString()
        )
        assertEquals(
            "1",
            StringBuilder("١").toIsoNumerals(NumeralSystem.EASTERN_ARABIC).toString()
        )
        assertEquals(
            "25",
            StringBuilder("٢٥").toIsoNumerals(NumeralSystem.EASTERN_ARABIC).toString()
        )
        assertEquals(
            "255",
            StringBuilder("٢٥٥").toIsoNumerals(NumeralSystem.EASTERN_ARABIC).toString()
        )
        assertEquals(
            "1255",
            StringBuilder("١٢٥٥").toIsoNumerals(NumeralSystem.EASTERN_ARABIC).toString()
        )

        assertEquals(
            "0",
            StringBuilder("๐").toIsoNumerals(NumeralSystem.THAI).toString()
        )
        assertEquals(
            "1",
            StringBuilder("๑").toIsoNumerals(NumeralSystem.THAI).toString()
        )
        assertEquals(
            "25",
            StringBuilder("๒๕").toIsoNumerals(NumeralSystem.THAI).toString()
        )
        assertEquals(
            "255",
            StringBuilder("๒๕๕").toIsoNumerals(NumeralSystem.THAI).toString()
        )
        assertEquals(
            "1255",
            StringBuilder("๑๒๕๕").toIsoNumerals(NumeralSystem.THAI).toString()
        )
    }

    @Test
    fun String_toIsoNumerals() {
        assertEquals("0", "٠".toIsoNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("1", "١".toIsoNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("25", "٢٥".toIsoNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("255", "٢٥٥".toIsoNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("1255", "١٢٥٥".toIsoNumerals(NumeralSystem.EASTERN_ARABIC))

        assertEquals("0", "๐".toIsoNumerals(NumeralSystem.THAI))
        assertEquals("1", "๑".toIsoNumerals(NumeralSystem.THAI))
        assertEquals("25", "๒๕".toIsoNumerals(NumeralSystem.THAI))
        assertEquals("255", "๒๕๕".toIsoNumerals(NumeralSystem.THAI))
        assertEquals("1255", "๑๒๕๕".toIsoNumerals(NumeralSystem.THAI))
    }

    @Test
    fun StringBuilder_toLocalNumerals() {
        assertEquals(
            "U popa bula sobaka",
            StringBuilder("U popa bula sobaka")
                .toLocalNumerals(NumeralSystem.WESTERN_ARABIC)
                .toString()
        )
        assertEquals(
            "",
            StringBuilder("")
                .toLocalNumerals(NumeralSystem.WESTERN_ARABIC)
                .toString()
        )
        assertEquals(
            "0",
            StringBuilder("0")
                .toLocalNumerals(NumeralSystem.WESTERN_ARABIC)
                .toString()
        )
        assertEquals(
            "1",
            StringBuilder("1")
                .toLocalNumerals(NumeralSystem.WESTERN_ARABIC)
                .toString()
        )
        assertEquals(
            "25",
            StringBuilder("25").toLocalNumerals(NumeralSystem.WESTERN_ARABIC).toString()
        )
        assertEquals(
            "255",
            StringBuilder("255").toLocalNumerals(NumeralSystem.WESTERN_ARABIC).toString()
        )
        assertEquals(
            "1255",
            StringBuilder("1255").toLocalNumerals(NumeralSystem.WESTERN_ARABIC).toString()
        )

        assertEquals(
            "٠",
            StringBuilder("0").toLocalNumerals(NumeralSystem.EASTERN_ARABIC).toString()
        )
        assertEquals(
            "١",
            StringBuilder("1").toLocalNumerals(NumeralSystem.EASTERN_ARABIC).toString()
        )
        assertEquals(
            "٢٥",
            StringBuilder("25").toLocalNumerals(NumeralSystem.EASTERN_ARABIC).toString()
        )
        assertEquals(
            "٢٥٥",
            StringBuilder("255").toLocalNumerals(NumeralSystem.EASTERN_ARABIC).toString()
        )
        assertEquals(
            "١٢٥٥",
            StringBuilder("1255").toLocalNumerals(NumeralSystem.EASTERN_ARABIC).toString()
        )

        assertEquals(
            "๐",
            StringBuilder("0").toLocalNumerals(NumeralSystem.THAI).toString()
        )
        assertEquals(
            "๑",
            StringBuilder("1").toLocalNumerals(NumeralSystem.THAI).toString()
        )
        assertEquals(
            "๒๕",
            StringBuilder("25").toLocalNumerals(NumeralSystem.THAI).toString()
        )
        assertEquals(
            "๒๕๕",
            StringBuilder("255").toLocalNumerals(NumeralSystem.THAI).toString()
        )
        assertEquals(
            "๑๒๕๕",
            StringBuilder("1255").toLocalNumerals(NumeralSystem.THAI).toString()
        )
    }

    @Test
    fun Array_toLocalNumerals() {
        assertTrue(
            arrayOf(
                "",
                "0",
                "1",
                "25",
                "255",
                "1255"
            ).contentEquals(
                arrayOf(
                    "",
                    "0",
                    "1",
                    "25",
                    "255",
                    "1255"
                ).toLocalNumerals(NumeralSystem.WESTERN_ARABIC)
            )
        )

        assertTrue(
            arrayOf(
                "",
                "٠",
                "١",
                "٢٥",
                "٢٥٥",
                "١٢٥٥"
            ).contentEquals(
                arrayOf(
                    "",
                    "0",
                    "1",
                    "25",
                    "255",
                    "1255"
                ).toLocalNumerals(NumeralSystem.EASTERN_ARABIC)
            )
        )

        assertTrue(
            arrayOf(
                "",
                "๐",
                "๑",
                "๒๕",
                "๒๕๕",
                "๑๒๕๕"
            ).contentEquals(
                arrayOf(
                    "",
                    "0",
                    "1",
                    "25",
                    "255",
                    "1255"
                ).toLocalNumerals(NumeralSystem.THAI)
            )
        )
    }

    @Test
    fun String_toLocalNumerals() {
        assertEquals("U popa bula sobaka", "U popa bula sobaka".toLocalNumerals(NumeralSystem.WESTERN_ARABIC))

        assertEquals("", "".toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("0", "0".toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("1", "1".toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("25", "25".toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("255", "255".toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("1255", "1255".toLocalNumerals(NumeralSystem.WESTERN_ARABIC))

        assertEquals("٠", "0".toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("١", "1".toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٢٥", "25".toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٢٥٥", "255".toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("١٢٥٥", "1255".toLocalNumerals(NumeralSystem.EASTERN_ARABIC))

        assertEquals("๐", "0".toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๑", "1".toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๒๕", "25".toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๒๕๕", "255".toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๑๒๕๕", "1255".toLocalNumerals(NumeralSystem.THAI))
    }

    @Test
    fun Int_toLocalNumerals() {
        assertEquals("0", 0.toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("1", 1.toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("25", 25.toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("255", 255.toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("1255", 1255.toLocalNumerals(NumeralSystem.WESTERN_ARABIC))

        assertEquals("٠", 0.toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("١", 1.toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٢٥", 25.toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٢٥٥", 255.toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("١٢٥٥", 1255.toLocalNumerals(NumeralSystem.EASTERN_ARABIC))

        assertEquals("๐", 0.toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๑", 1.toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๒๕", 25.toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๒๕๕", 255.toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๑๒๕๕", 1255.toLocalNumerals(NumeralSystem.THAI))

        assertEquals("૦", 0.toLocalNumerals(NumeralSystem.GUJARATI))
        assertEquals("૧", 1.toLocalNumerals(NumeralSystem.GUJARATI))
        assertEquals("૨૫", 25.toLocalNumerals(NumeralSystem.GUJARATI))
        assertEquals("૨૫૫", 255.toLocalNumerals(NumeralSystem.GUJARATI))
        assertEquals("૧૨૫૫", 1255.toLocalNumerals(NumeralSystem.GUJARATI))
    }

    @Test
    fun Long_toLocalNumerals() {
        assertEquals("0", 0L.toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("1", 1L.toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("25", 25L.toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("255", 255L.toLocalNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("1255", 1255L.toLocalNumerals(NumeralSystem.WESTERN_ARABIC))

        assertEquals("٠", 0L.toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("١", 1L.toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٢٥", 25L.toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٢٥٥", 255L.toLocalNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("١٢٥٥", 1255L.toLocalNumerals(NumeralSystem.EASTERN_ARABIC))

        assertEquals("๐", 0L.toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๑", 1L.toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๒๕", 25L.toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๒๕๕", 255L.toLocalNumerals(NumeralSystem.THAI))
        assertEquals("๑๒๕๕", 1255L.toLocalNumerals(NumeralSystem.THAI))
    }

    @Test
    fun toLocal2DigNumerals() {
        assertEquals("00", 0.toLocal2DigNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("01", 1.toLocal2DigNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("25", 25.toLocal2DigNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("255", 255.toLocal2DigNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("1255", 1255.toLocal2DigNumerals(NumeralSystem.WESTERN_ARABIC))

        assertEquals("٠٠", 0.toLocal2DigNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٠١", 1.toLocal2DigNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٢٥", 25.toLocal2DigNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٢٥٥", 255.toLocal2DigNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("١٢٥٥", 1255.toLocal2DigNumerals(NumeralSystem.EASTERN_ARABIC))
    }

    @Test
    fun toLocal3DigNumerals() {
        assertEquals("000", 0.toLocal3DigNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("001", 1.toLocal3DigNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("025", 25.toLocal3DigNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("255", 255.toLocal3DigNumerals(NumeralSystem.WESTERN_ARABIC))
        assertEquals("1255", 1255.toLocal3DigNumerals(NumeralSystem.WESTERN_ARABIC))

        assertEquals("٠٠٠", 0.toLocal3DigNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٠٠١", 1.toLocal3DigNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٠٢٥", 25.toLocal3DigNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("٢٥٥", 255.toLocal3DigNumerals(NumeralSystem.EASTERN_ARABIC))
        assertEquals("١٢٥٥", 1255.toLocal3DigNumerals(NumeralSystem.EASTERN_ARABIC))
    }
}