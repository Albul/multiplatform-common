package com.olekdia.common.extensions

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