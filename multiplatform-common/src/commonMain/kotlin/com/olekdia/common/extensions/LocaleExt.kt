@file:JvmName("LocaleExt")
package com.olekdia.common.extensions

import com.olekdia.common.NINE_DIGIT
import com.olekdia.common.NumeralSystem.Companion.DEVANAGARI
import com.olekdia.common.NumeralSystem.Companion.EASTERN_ARABIC
import com.olekdia.common.NumeralSystem.Companion.GUJARATI
import com.olekdia.common.NumeralSystem.Companion.PERSO_ARABIC
import com.olekdia.common.NumeralSystem.Companion.THAI
import com.olekdia.common.NumeralSystem.Companion.WESTERN_ARABIC
import com.olekdia.common.ZERO_DIGIT
import kotlin.jvm.JvmName

val DIGITS_WESTERN_ARABIC = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
val DIGITS_EASTERN_ARABIC = charArrayOf('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')
val DIGITS_PERSO_ARABIC = charArrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
val DIGITS_GUJARATI = charArrayOf('૦', '૧', '૨', '૩', '૪', '૫', '૬', '૭', '૮', '૯')
val DIGITS_DEVANAGARI = charArrayOf('०', '१', '२', '३', '४', '५', '६', '७', '८', '९')
val DIGITS_THAI = charArrayOf('๐', '๑', '๒', '๓', '๔', '๕', '๖', '๗', '๘', '๙')

private val builder = StringBuilder(100)

private fun getDigits(numSystem: Int): CharArray =
    when (numSystem) {
        WESTERN_ARABIC -> DIGITS_WESTERN_ARABIC
        EASTERN_ARABIC -> DIGITS_EASTERN_ARABIC
        PERSO_ARABIC -> DIGITS_PERSO_ARABIC
        GUJARATI -> DIGITS_GUJARATI
        DEVANAGARI -> DIGITS_DEVANAGARI
        THAI -> DIGITS_THAI
        else -> DIGITS_WESTERN_ARABIC
    }

/**
 * Converts only to western arabic
 */
@ExperimentalStdlibApi
private fun StringBuilder.toIsoNumerals(numSystem: Int) {
    val count = this.length
    if (count == 0 || numSystem == WESTERN_ARABIC) return
    val digits = getDigits(numSystem)

    var currChar: Char
    for (i in 0 until count) {
        currChar = this[i]

        if (currChar >= digits[0] && currChar <= digits[9]) {
            this.set(i, Character.forDigit(currChar - digits[0], 10))
        }
    }
}

fun CharSequence.toIsoNumerals(numSystem: Int): CharSequence =
    if (numSystem == WESTERN_ARABIC) {
        this
    } else {
        builder
            .clear()
            .append(this)
            .toIsoNumerals(numSystem)
            .toString()
    }

/**
 * Converts only from western arabic
 */
@ExperimentalStdlibApi
fun StringBuilder.toLocalNumerals(numSystem: Int): StringBuilder {
    val count = this.length
    if (count == 0 || numSystem == WESTERN_ARABIC) return this
    val digits = getDigits(numSystem)

    var currChar: Char
    for (i in 0 until count) {
        currChar = this[i]

        if (currChar in ZERO_DIGIT..NINE_DIGIT) {
            this[i] = digits[currChar - ZERO_DIGIT]
        }
    }

    return this
}

fun String.toLocalNumerals(numSystem: Int): String =
    if (numSystem == WESTERN_ARABIC) {
        this
    } else {
        builder
            .clear()
            .append(this)
            .toLocalNumerals(numSystem)
            .toString()
    }

fun CharSequence.toLocalNumerals(numSystem: Int): CharSequence =
    if (numSystem == WESTERN_ARABIC) {
        this
    } else {
        builder
            .clear()
            .append(this)
            .toLocalNumerals(numSystem)
    }

fun Array<CharSequence>.toLocalNumerals(numSystem: Int): Array<CharSequence> =
    if (numSystem == WESTERN_ARABIC) {
        this
    } else {
        for (i in this.indices) {
            this[i] = this[i].toLocalNumerals(numSystem)
        }
        this
    }

fun Int.toLocalNumerals(numSystem: Int): String =
    builder
        .clear()
        .append(this)
        .toLocalNumerals(numSystem)
        .toString()

fun Long.toLocalNumerals(numSystem: Int): String =
    builder
        .clear()
        .append(this)
        .toLocalNumerals(numSystem)
        .toString()

/**
 * @format 3 > 03, 10 > 10, 0 > 00
 */
fun Int.toLocal2DigNumerals(numSystem: Int): String =
    builder
        .clear()
        .also {
            when {
                this <= 0 ->
                    it
                        .append(ZERO_DIGIT)
                        .append(ZERO_DIGIT)

                this < 10 ->
                    it
                        .append(ZERO_DIGIT)
                        .append(this)

                this >= 10 -> it.append(this)
            }
        }
        .toLocalNumerals(numSystem)
        .toString()

fun Int.toLocal3DigNumerals(numSystem: Int): String =
    builder
        .clear()
        .also {
            when {
                this <= 0 ->
                    it
                        .append(ZERO_DIGIT)
                        .append(ZERO_DIGIT)
                        .append(ZERO_DIGIT)

                this < 10 ->
                    it
                        .append(ZERO_DIGIT)
                        .append(ZERO_DIGIT)
                        .append(this)

                this in 10..99 ->
                    it
                        .append(ZERO_DIGIT)
                        .append(this)

                this >= 100 -> it.append(this)
            }
        }
        .toLocalNumerals(numSystem)
        .toString()