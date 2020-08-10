package com.olekdia.common.extensions

import com.olekdia.common.*
import com.olekdia.common.misc.Path
import kotlin.jvm.JvmOverloads

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
fun String?.containsNullable(searched: String?, ignoreCase: Boolean = true): Boolean {
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

                for (i in this.length - length downTo 0) {
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

@JvmOverloads
fun String?.trimBidi(replaceWith: String = ""): String = this
    ?.let {
        it
            .replace(LRE.toString(), replaceWith)
            .replace(RLE.toString(), replaceWith)
            .replace(PDF.toString(), replaceWith)
            .replace(PDI.toString(), replaceWith)
            .replace(LRM.toString(), replaceWith)
            .replace(RLM.toString(), replaceWith)
            .replace(LRI.toString(), replaceWith)
            .replace(RLI.toString(), replaceWith)
            .replace(FSI.toString(), replaceWith)
            .trim { it <= ' ' }
    }
    ?: ""

fun String.toPath(): Path = Path(this)