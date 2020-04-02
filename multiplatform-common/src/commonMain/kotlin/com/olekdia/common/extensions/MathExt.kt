@file:Suppress("NOTHING_TO_INLINE")
@file:JvmName("MathExt")
package com.olekdia.common.extensions

import kotlin.jvm.JvmName
import kotlin.math.*
import kotlin.random.Random

/**
 * @param places more or equals 0
 * @return rounded number
 */
fun Double.round(places: Int): Double {
    var value = this
    require(places >= 0)

    val factor: Double = 10.0.pow(places)
    value *= factor
    return value.roundToLong() / factor
}
/**
 * @param places more or equals 0
 * @return rounded number
 */
fun Float.round(places: Int): Float {
    var value = this
    require(places >= 0)

    val factor: Float = 10.0.pow(places).toFloat()
    value *= factor
    return value.roundToInt() / factor
}

/**
 * @param to not equals 0
 * @return number rounded to the input argument
 */
infix fun Int.roundTo(to: Int): Int = this - this % to
/**
 * @param to not equals 0
 * @return number rounded to the input argument
 */
infix fun Long.roundTo(to: Long): Long = this - this % to
/**
 * @param to not equals 0
 * @return number rounded to the input argument, approximate result due to rounding error
 */
infix fun Float.roundTo(to: Float): Float = this - this % to
/**
 * @param to not equals 0
 * @return number rounded to the input argument
 */
infix fun Float.roundTo(to: Int): Int = (this - this % to).roundToInt()
/**
 * @param to not equals 0
 * @return number rounded to the input argument
 */
infix fun Double.roundTo(to: Int): Long = (this - this % to).roundToLong()

infix fun Float.roundToCeil(to: Int): Int =
    if (this % to == 0F) {
        this.toInt()
    } else {
        (this - this % to).roundToInt() + to
    }

/**
 * @receiver a number of percents
 * @param value a number
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

fun max(vararg args: Int): Int {
    var maxResult = Int.MIN_VALUE

    for (arg in args) {
        if (arg > maxResult) {
            maxResult = arg
        }
    }

    return maxResult
}

//--------------------------------------------------------------------------------------------------
//  getRandom
//--------------------------------------------------------------------------------------------------

/**
 * @throws IllegalArgumentException if max < min
 * @return Random Int from range min [inclusive], max [inclusive]
 */
fun getRandom(min: Int, max: Int): Int =
    require(min <= max)
        .let { Random.nextInt(min, max + 1) }
/**
 * @throws IllegalArgumentException if max < min
 * @return Random Long from range min [inclusive], max [inclusive]
 */
fun getRandom(min: Long, max: Long): Long =
    require(min <= max)
        .let { Random.nextLong(min, max + 1) }
/**
 * @throws IllegalArgumentException if max < min
 * @return Random Float from range min [inclusive], max [inclusive]
 */
fun getRandom(min: Float, max: Float): Float =
    require(min <= max)
        .let { Random.nextFloat() * (max - min) + min }
/**
 * @throws IllegalArgumentException if max < min
 * @return Random Double from range min [inclusive], max [inclusive]
 */
fun getRandom(min: Double, max: Double): Double =
    require(min <= max)
        .let { Random.nextDouble() * (max - min) + min }
/**
 * @return Boolean true or false
 */
fun getRandom(): Boolean = Random.nextBoolean()

/**
 * @throws IllegalArgumentException if array is empty
 * @return Random value from array
 */
fun getRandom(array: IntArray): Int =
    require(array.isNotEmpty())
        .let { array[Random.nextInt(0, array.size)] }

/**
 * @throws IllegalArgumentException if array is empty
 * @return Random value from array
 */
fun getRandom(array: LongArray): Long =
    require(array.isNotEmpty())
        .let { array[Random.nextInt(0, array.size)] }

/**
 * @throws IllegalArgumentException if array is empty
 * @return Random value from array
 */
fun getRandom(array: FloatArray): Float =
    require(array.isNotEmpty())
        .let { array[Random.nextInt(0, array.size)] }

/**
 * @throws IllegalArgumentException if array is empty
 * @return Random value from array
 */
fun getRandom(array: DoubleArray): Double =
    require(array.isNotEmpty())
        .let { array[Random.nextInt(0, array.size)] }

/**
 * @throws IllegalArgumentException if array is empty
 * @return Random value from array
 */
fun getRandom(array: CharArray): Char =
    require(array.isNotEmpty())
        .let { array[Random.nextInt(0, array.size)] }

/**
 * @throws IllegalArgumentException if array is empty
 * @return Random value from array
 */
fun getRandom(array: BooleanArray): Boolean =
    require(array.isNotEmpty())
        .let { array[Random.nextInt(0, array.size)] }

/**
 * @throws IllegalArgumentException if array is empty
 * @return Random value from array
 */
fun getRandom(array: Array<String>): String =
    require(array.isNotEmpty())
        .let { array[Random.nextInt(0, array.size)] }

/**
 * @throws IllegalArgumentException if array is empty
 * @return Random value from array
 */
fun getRandom(array: Array<Any>): Any =
    require(array.isNotEmpty())
        .let { array[Random.nextInt(0, array.size)] }

//--------------------------------------------------------------------------------------------------

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
fun lcm(a: Int, b: Int): Int =
    a / gcd(a, b) * b

/**
 * Least common multiple
 */
fun lcm(a: Int, b: Int, c: Int): Int =
    lcm(a, lcm(b, c))

fun geomProgressionItemN(item1: Long, q: Double, n: Int): Double =
    item1 * q.pow((n - 1).toDouble())

fun Int.factorial(): Long {
    var num: Long = this.toLong()
    var fact: Long = 1L

    while (num > 0) {
        fact *= num--
    }

    return fact
}

fun combination(n: Int, k: Int): Long =
    n.factorial() / (k.factorial() * (n - k).factorial())