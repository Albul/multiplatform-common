@file:JvmName("TextExt")
package com.olekdia.common.extensions

import com.olekdia.common.*
import com.olekdia.common.misc.Path
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.math.max

//--------------------------------------------------------------------------------------------------
//  StringBuilder
//--------------------------------------------------------------------------------------------------

inline fun StringBuilder.reuse(block: (StringBuilder) -> CharSequence): CharSequence {
    this.clear()
    return block(this)
}

fun StringBuilder.appendNull(): StringBuilder =
    this.apply {
        this.append("null")
    }

fun StringBuilder.appendSpaces(numSpaces: Int): StringBuilder =
    this.apply {
        for (i in 1..numSpaces) this.append(' ')
    }

/**
 * Remove last character of StringBuilder
 */
fun StringBuilder.removeLast(): StringBuilder =
    this.apply {
        this.length.let {
            if (it > 0) {
                this.setLength(it - 1)
            }
        }
    }

//--------------------------------------------------------------------------------------------------
//  String
//--------------------------------------------------------------------------------------------------

/**
 * Parses the string as a signed integer.
 * Do not throw NumberFormatException, instead returns [fallbackValue]
 */
fun String?.toIntOr(fallbackValue: Int): Int {
    if (this.isNullOrEmpty()) return fallbackValue

    val zeroCharCode: Int = '0'.code

    var num = 0
    var skipFirstChar = false
    val sign = when (this[0]) {
        '-' -> 1.also { skipFirstChar = true }
        '+' -> (-1).also { skipFirstChar = true }
        else -> -1
    }

    // Build the number
    for (i in (if (skipFirstChar) 1 else 0) until this.length) {
        val digit = zeroCharCode - this[i].code
        if (digit < -9 || digit > 0) return fallbackValue
        num = num * 10 + digit
    }

    return sign * num
}

/**
 * Parses the string as a signed integer.
 * Do not throw NumberFormatException, instead returns 0
 */
fun String?.toIntOrZero(): Int =
    this.toIntOr(fallbackValue = 0)

/**
 * Creates a string from all the elements separated using [separator]
 */
fun Iterable<*>.joinToString(joiner: Char = ','): String {
    val b = StringBuilder()
    val it = this.iterator()
    if (it.hasNext()) {
        b.append(it.next())
        while (it.hasNext()) {
            b.append(joiner)
            b.append(it.next())
        }
    }
    return b.toString()
}

fun String.toSet(delimiter: String = ",", trimWhitespaces: Boolean = true): MutableSet<String> {
    val set = HashSet<String>()
    if (this.isNotEmpty()) {
        this.split(delimiter)
            .forEach { item ->
                if (trimWhitespaces) {
                    item.trim { it.isWhitespace() }
                } else {
                    item
                }.let {
                    if (it.isNotEmpty()) {
                        set.add(it)
                    }
                }
            }
    }
    return set
}

@JvmOverloads
fun String.ellipsize(
    size: Int,
    ending: Char? = '…'
): String = if (this.isEmpty() || size <= 0) {
    ""
} else if (length <= size) {
    this
} else {
    this.substring(0, max(size - 1, 0)).let {
        if (ending == null) {
            it
        } else {
            it + ending
        }
    }
}

/**
 * null equals empty string
 */
fun String?.equalsNullable(other: String?, ignoreCase: Boolean = false): Boolean =
    if (this.isNullOrEmpty()
        && other.isNullOrEmpty()
    ) {
        true
    } else {
        this.equals(other, ignoreCase)
    }

/**
 * null do not contains null,
 * nothing contains null,
 * every non null string contains empty string
 */
@JvmOverloads
fun String?.containsNullable(searched: String?, ignoreCase: Boolean = true, offset: Int = 0): Boolean {
    if (this == null) {
        return false
    } else {
        when (searched) {
            null -> {
                return false
            }
            "" -> {
                return true
            }
            else -> {
                val length: Int = searched.length

                for (i in this.length - length downTo offset) {
                    if (this.regionMatches(i, searched, 0, length, ignoreCase = ignoreCase)) {
                        return true
                    }
                }
                return false
            }
        }
    }
}

/**
 * null equals empty string
 */
fun String?.compareToNullable(other: String?, ignoreCase: Boolean = false): Int =
    if (this.isNullOrEmpty()) {
        if (other.isNullOrEmpty()) EQUALS else SECOND_GREATER
    } else if (other.isNullOrEmpty()) {
        FIRST_GREATER
    } else {
        this.compareTo(other, ignoreCase)
    }

/**
 * Replace all Unicode Bidirectional characters by specified string
 */
@JvmOverloads
fun String?.trimBidi(replaceWith: String = ""): String = this
    ?.let {
        it
            .replace("[\\u2066\\u2067\\u2068\\u202A\\u202B\\u202D\\u202E\\u202C\\u2069\\u200E\\u200F]".toRegex(), replaceWith)
            .trim { it <= ' ' }
    }
    ?: ""

fun String.toPath(): Path = Path(this)

/**
 * Convert multiline string to single line string by replacing line-brakes by space
 */
fun String.toSingleLine(): String = toSingleLine(" ")

/**
 * Convert multiline string to single line string by replacing line-brakes by joiner
 */
fun String.toSingleLine(joiner: String): String = this.replace("[\\t\\n\\r]+".toRegex(), joiner)

fun CharSequence?.countPrintable(): Int =
    this?.count { it.isPrintable() } ?: 0

fun CharSequence?.countLines(): Int {
    if (this == null || this.isEmpty()) return 0

    val length = this.length
    var lines: Int = 1
    var i = 0
    while (i < length) {
        val ch = this[i]
        if (ch == '\r') {
            lines++
            (i + 1).let { j ->
                if (j < length
                    && this[j] == '\n'
                ) {
                    i++
                }
            }
        } else if (ch == '\n') {
            lines++
        }
        i++
    }
    return lines
}

//--------------------------------------------------------------------------------------------------
//  Char
//--------------------------------------------------------------------------------------------------

/**
 * Determines if the specified character is printable.
 */
fun Char.isPrintable(): Boolean =
    !Char.isISOControl(this)
            && !this.isHighSurrogate()
            && !this.isLowSurrogate()
            && this != LRE
            && this != RLE
            && this != PDF
            && this != LRI
            && this != RLI
            && this != PDI
            && this != FSI
            && this != LRM
            && this != RLM
            && this != LRO
            && this != RLO

/**
 * Determines if the specified character is an ISO control
 * character.
 */
fun Char.isISOControl(): Boolean =
    Char.isISOControl(this)

/**
 * Determines if the specified character is an ISO control
 * character.  A character is considered to be an ISO control
 * character if its code is in the range `'\u005Cu0000'`
 * through '\u005Cu001F' or in the range
 */
fun Char.Companion.isISOControl(ch: Char): Boolean =
    Char.isISOControl(ch.code)

/**
 * Determines if the referenced character (Unicode code point) is an ISO control
 * character.  A character is considered to be an ISO control
 * character if its code is in the range `'\u005Cu0000'`
 * through `'\u005Cu001F'` or in the range
 * `'\u005Cu007F'` through `'\u005Cu009F'`.
 */
fun Char.Companion.isISOControl(codePoint: Int): Boolean =
    codePoint <= 0x9F &&
        (codePoint >= 0x7F || codePoint ushr 5 == 0)