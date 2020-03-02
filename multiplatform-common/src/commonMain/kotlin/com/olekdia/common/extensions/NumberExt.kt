@file:Suppress("NOTHING_TO_INLINE")
@file:JvmName("NumExt")
package com.olekdia.common.extensions

import kotlin.jvm.JvmName
import kotlin.math.floor
import kotlin.math.log10

inline val Int.u: Int get() = this

inline val Long.u: Int get() = toInt()

inline val Int.zeroBased: Int get() = this - 1

inline val Int.oneBased: Int get() = this + 1

inline val Int.sign: Int
    get() = when {
        this > 0 -> 1
        this < 0 -> -1
        else -> 0
    }

inline val Long.sign: Int
    get() = when {
        this > 0 -> 1
        this < 0 -> -1
        else -> 0
    }

inline fun Boolean.toInt(): Int = if (this) 1 else 0

inline fun Int.toBoolean(): Boolean = this > 0

inline fun Long.positiveOrZero(): Long = if (this > 0) this else 0
inline fun Long.negativeOrZero(): Long = if (this < 0) this else 0

inline fun Int.positiveOrZero(): Int = if (this > 0) this else 0
inline fun Int.negativeOrZero(): Int = if (this < 0) this else 0
/**
 * @return true if Double has integer value, like 3.0, 1.0
 */
fun Double.isRounded() = this == this.toInt().toDouble()
/**
 * @return true if Float has integer value, like 3.0f, 1.0f
 */
fun Float.isRounded() = this == this.toInt().toFloat()

/* To get integer part of a float */
val Double.intPart: Int
        get() = if (this >= 0) floor(this).toInt() else floor(this).toInt() + 1

/**
 * @return number of zero bits counted from the right to left
 */
expect fun Int.numberOfTrailingZeros(): Int

/**
 * @return numbers of digits in the number
 */
fun Int.numberOfDigits(): Int =
    when {
        this == 0 -> 1
        this < 0 -> log10((-this).toDouble()).toInt() + 2
        else -> log10(this.toDouble()).toInt() + 1
    }