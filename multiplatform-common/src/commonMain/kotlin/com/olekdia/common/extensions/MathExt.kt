@file:Suppress("NOTHING_TO_INLINE")
@file:JvmName("MathExt")
package com.olekdia.common.extensions

import kotlin.jvm.JvmName
import kotlin.math.*
import kotlin.random.Random

fun Double.round(places: Int): Double {
    var value = this
    require(places >= 0)

    val factor: Double = 10.0.pow(places)
    value *= factor
    return value.roundToLong() / factor
}

fun Float.round(places: Int): Float {
    var value = this
    require(places >= 0)

    val factor: Float = 10.0.pow(places).toFloat()
    value *= factor
    return value.roundToInt() / factor
}

infix fun Float.roundTo(to: Float): Float = this - abs(this % to)

infix fun Float.roundTo(to: Int): Int = (this - this % to).roundToInt()

infix fun Double.roundTo(to: Int): Long = (this - this % to).roundToLong()

infix fun Int.roundTo(to: Int): Int = this - this % to

infix fun Float.roundToCeil(to: Int): Int =
    if (this % to == 0F) {
        this.toInt()
    } else {
        (this - this % to).roundToInt() + to
    }

/**
 * @return percent of a number
 */
infix fun Int.percentOf(value: Int): Int = (value.toLong() * this / 100L).toInt()

/**
 * @return percent of a number. It gives approximate results for numbers bigger than 92233720000000000L
 */
infix fun Int.percentOf(value: Long): Long =
    if (abs(value) > 92233720368547000L) {
        value / 100L * this
    } else {
        value * this / 100L
    }

infix fun Int.divCeil(to: Int): Int = this / to + if (this % to == 0) 0 else 1

fun Float.normalize(min: Float, max: Float): Float = this * (max - min) + min

fun log(a: Int, x: Int): Int = (ln(x.toDouble()) / ln(a.toDouble())).toInt()

fun min(vararg args: Int): Int {
    var minResult = Int.MAX_VALUE

    for (arg in args) {
        if (arg < minResult) {
            minResult = arg
        }
    }

    return minResult
}

/**
 * @return Random Int from range min [inclusive], max [inclusive]
 */
fun getRandom(min: Int, max: Int): Int = Random.nextInt(min, max + 1)
/**
 * @return Random Long from range min [inclusive], max [inclusive]
 */
fun getRandom(min: Long, max: Long): Long = Random.nextLong(min, max + 1)
/**
 * @return Random Float from range min [inclusive], max [inclusive]
 */
fun getRandom(min: Float, max: Float): Float = Random.nextFloat() * (max - min) + min
/**
 * @return Random Double from range min [inclusive], max [inclusive]
 */
fun getRandom(min: Double, max: Double): Double = Random.nextDouble() * (max - min) + min
/**
 * @return Boolean true or false
 */
fun getRandom(): Boolean = Random.nextBoolean()

inline fun Double.toRadians(): Double = this * kotlin.math.PI / 180.0
inline fun Float.toRadians(): Float = this * com.olekdia.common.PI / 180F

fun isInCircle(x: Float, y: Float, cx: Float, cy: Float, r: Float): Boolean =
    (x - cx) * (x - cx) + (y - cy) * (y - cy) <= r * r

fun segmentLength(x1: Float, y1: Float, x2: Float, y2: Float): Double =
    sqrt((x1 - x2).toDouble() * (x1 - x2) + (y1 - y2) * (y1 - y2))

/**
 * Greatest common divisor
 */
fun gcd(a: Int, b: Int): Int {
    var varA = a
    var varB = b
    var tmp: Int

    while (varA != 0 && varB != 0) {
        varA %= varB
        tmp = varA
        varA = varB
        varB = tmp
    }
    return varA + varB
}

/**
 * Least common multiple
 */
fun lcm(a: Int, b: Int): Int = a / gcd(a, b) * b

/**
 * Least common multiple
 */
fun lcm(a: Int, b: Int, c: Int): Int = lcm(a, lcm(b, c))

fun geomProgressionItemN(item1: Long, q: Double, n: Int): Double = item1 * q.pow((n - 1).toDouble())

fun Int.factorial(): Long {
    var num: Long = this.toLong()
    var fact: Long = 1L

    while (num > 0) {
        fact *= num--
    }

    return fact
}