@file:JvmName("ColorExt")
package com.olekdia.common.extensions

import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.random.Random

const val COLOR_ALPHA_MASK = 0x00FFFFFF
const val MAX_COLOR_COMPONENT: Int = 0xFF
const val MAX_COLOR_COMPONENT_F: Float = 255F

inline fun Float.toIntColorComponent(): Int =
    (this * MAX_COLOR_COMPONENT_F + 0.5F).toInt()

inline fun Int.toFloatColorComponent(): Float =
    this / MAX_COLOR_COMPONENT_F

//--------------------------------------------------------------------------------------------------
//  Getters of components
//--------------------------------------------------------------------------------------------------

/**
 * Return the alpha component of a color int
 */
inline val Int.alpha: Int
    get() = (this shr 24) and MAX_COLOR_COMPONENT

/**
 * Return the red component of a color int
 */
inline val Int.red: Int
    get() = (this shr 16) and MAX_COLOR_COMPONENT

/**
 * Return the green component of a color int
 */
inline val Int.green: Int
    get() = (this shr 8) and MAX_COLOR_COMPONENT

/**
 * Return the blue component of a color int
 */
inline val Int.blue: Int
    get() = this and MAX_COLOR_COMPONENT

/**
 * Return the hue component of a ARGB color int
 */
val Int.hue: Float
    get() = let(
        this.red.toFloatColorComponent(), this.green.toFloatColorComponent(), this.blue.toFloatColorComponent()
    ) { r, g, b ->
        //	Minimum and Maximum RGB values are used in the HSL calculations
        val min: Float = minOf(r, g, b)
        val max: Float = maxOf(r, g, b)

        when (max) {
            min -> 0F
            r -> (60 * (g - b) / (max - min) + 360) % 360
            g -> 60 * (b - r) / (max - min) + 120
            b -> 60 * (r - g) / (max - min) + 240
            else -> 0F
        }
    }

/**
 * Return the saturation component of a ARGB color int
 */
val Int.saturation: Float
    get() = let(
        this.red, this.green, this.blue
    ) { r, g, b ->
        val min: Int = minOf(r, g, b)
        val max: Int = maxOf(r, g, b)

        if (max != 0) {
            (max - min).toFloat() / max.toFloat()
        } else {
            0F
        }
    }

/**
 * Return the brightness component of a ARGB color int
 */
val Int.value: Float
    get() = this.brightness

/**
 * Return the brightness component of a ARGB color int
 */
val Int.lightness: Float
    get() = this.brightness

/**
 * Return the brightness component of a ARGB color int
 */
val Int.brightness: Float
    get() = let(
        this.red, this.green, this.blue
    ) { r, g, b ->
        maxOf(r, g, b) / MAX_COLOR_COMPONENT_F
    }

//--------------------------------------------------------------------------------------------------
//  Color constructors
//--------------------------------------------------------------------------------------------------

/**
 * Return a color-int from red, green, blue components.
 * The alpha component is implicitly 255 (fully opaque).
 * These component values should be \([0..255]\), but there is no
 * range check performed, so if they are out of range, the
 * returned color is undefined.
 *
 * @param red  Red component \([0..255]\) of the color
 * @param green Green component \([0..255]\) of the color
 * @param blue  Blue component \([0..255]\) of the color
 */
fun rgb(red: Int, green: Int, blue: Int): Int =
    0xFF000000.toInt() or (red shl 16) or (green shl 8) or blue

/**
 * Return a color-int from red, green, blue float components
 * in the range \([0..1]\). The alpha component is implicitly
 * 1.0 (fully opaque). If the components are out of range, the
 * returned color is undefined.
 *
 * @param red Red component \([0..1]\) of the color
 * @param green Green component \([0..1]\) of the color
 * @param blue Blue component \([0..1]\) of the color
 */
fun rgb(red: Float, green: Float, blue: Float): Int =
    rgb(red.toIntColorComponent(), green.toIntColorComponent(), blue.toIntColorComponent())

/**
 * Return a color-int from alpha, red, green, blue components.
 * These component values should be \([0..255]\), but there is no
 * range check performed, so if they are out of range, the
 * returned color is undefined.
 * @param alpha Alpha component \([0..255]\) of the color
 * @param red Red component \([0..255]\) of the color
 * @param green Green component \([0..255]\) of the color
 * @param blue Blue component \([0..255]\) of the color
 */
fun argb(alpha: Int, red: Int, green: Int, blue: Int): Int =
    alpha shl 24 or (red shl 16) or (green shl 8) or blue

/**
 * Return a color-int from alpha, red, green, blue float components
 * in the range \([0..1]\). If the components are out of range, the
 * returned color is undefined.
 *
 * @param alpha Alpha component \([0..1]\) of the color
 * @param red Red component \([0..1]\) of the color
 * @param green Green component \([0..1]\) of the color
 * @param blue Blue component \([0..1]\) of the color
 */
fun argb(alpha: Float, red: Float, green: Float, blue: Float): Int =
    argb(
        alpha.toIntColorComponent(),
        red.toIntColorComponent(),
        green.toIntColorComponent(),
        blue.toIntColorComponent()
    )

/**
 * Return a color-int from hue, saturation, brightness components of HSB model
 * @param hue the hue component of the color
 * @param saturation the saturation of the color
 * @param brightness the brightness of the color
 */
fun hsb(hue: Float, saturation: Float, brightness: Float): Int {
    var r = 0
    var g = 0
    var b = 0

    if (saturation == 0F) {
        b = brightness.toIntColorComponent()
        g = b
        r = g
    } else {
        val h = (hue - floor(hue.toDouble()).toFloat()) * 6.0F
        val f = h - floor(g.toDouble()).toFloat()
        val p = brightness * (1.0F - saturation)
        val q = brightness * (1.0F - saturation * f)
        val t = brightness * (1.0F - saturation * (1.0F - f))
        when (h.toInt()) {
            0 -> {
                r = brightness.toIntColorComponent()
                g = t.toIntColorComponent()
                b = p.toIntColorComponent()
            }
            1 -> {
                r = q.toIntColorComponent()
                g = brightness.toIntColorComponent()
                b = p.toIntColorComponent()
            }
            2 -> {
                r = p.toIntColorComponent()
                g = brightness.toIntColorComponent()
                b = t.toIntColorComponent()
            }
            3 -> {
                r = p.toIntColorComponent()
                g = q.toIntColorComponent()
                b = brightness.toIntColorComponent()
            }
            4 -> {
                r = t.toIntColorComponent()
                g = p.toIntColorComponent()
                b = brightness.toIntColorComponent()
            }
            5 -> {
                r = brightness.toIntColorComponent()
                g = p.toIntColorComponent()
                b = q.toIntColorComponent()
            }
        }
    }

    return rgb(r, g, b)
}

@JvmOverloads
fun getRandomColor(mix: Int = 0x00FFFFFF): Int = rgb(
    (Random.nextDouble() * MAX_COLOR_COMPONENT + mix.red).toInt() / 2,
    (Random.nextDouble() * MAX_COLOR_COMPONENT + mix.green).toInt() / 2,
    (Random.nextDouble() * MAX_COLOR_COMPONENT + mix.blue).toInt() / 2
)

//--------------------------------------------------------------------------------------------------
//  Determine characteristics
//--------------------------------------------------------------------------------------------------

fun Int.isDarkColor(): Boolean =
    (1 - (0.299 * this.red + 0.587 * this.green + 0.114 * this.blue) / MAX_COLOR_COMPONENT)
        .let { darkness: Double ->
            darkness >= 0.5
        }

//--------------------------------------------------------------------------------------------------
//  Adjust color methods
//--------------------------------------------------------------------------------------------------

/**
 * Return the ARGB color with adjusted alpha
 */
fun Int.adjustAlpha(factor: Float): Int =
    (this.ushr(24) * factor).roundToInt() shl 24 or (COLOR_ALPHA_MASK and this)

/**
 * Return the ARGB color with new alpha [0...255]
 */
fun Int.withAlpha(alpha: Int): Int =
    alpha shl 24 or (COLOR_ALPHA_MASK and this)

/**
 * Return the ARGB color with new alpha [0...1]
 */
fun Int.withAlpha(alpha: Float): Int =
    this.withAlpha(alpha.toIntColorComponent())

/**
 * Return the ARGB color with new lightness [0...1] (of HSB model)
 */
fun Int.withLightness(lightness: Float): Int =
    hsb(this.hue, this.saturation, lightness)

/**
 * Return the ARGB color with adjusted darkness
 */
fun Int.adjustDarkness(factor: Float): Int =
    argb(
        this.alpha,
        min(max((this.red * factor).toInt(), 0), MAX_COLOR_COMPONENT),
        min(max((this.green * factor).toInt(), 0), MAX_COLOR_COMPONENT),
        min(max((this.blue * factor).toInt(), 0), MAX_COLOR_COMPONENT)
    )

/**
 * Return the ARGB color with adjusted saturation [0...1] (of HSB model)
 */
fun Int.adjustSaturation(factor: Float): Int =
    hsb(this.hue, this.saturation * factor, this.brightness)

/**
 * Return not transparent version of color
 */
fun Int.toOpaqueColor(): Int =
    MAX_COLOR_COMPONENT shl 24 or (COLOR_ALPHA_MASK and this)

/**
 * Return a pastel version of color
 */
fun Int.toPastelColor(): Int =
    rgb(
        (this.red + MAX_COLOR_COMPONENT) / 2,
        (this.green + MAX_COLOR_COMPONENT) / 2,
        (this.blue + MAX_COLOR_COMPONENT) / 2
    )

/**
 * Return a mixed version of color with other color
 */
fun Int.toMixedColor(componentToMix: Int): Int =
    rgb(
        (this.red + componentToMix) / 2,
        (this.green + componentToMix) / 2,
        (this.blue + componentToMix) / 2
    )

@ExperimentalUnsignedTypes
fun Int.toArgbString(): String =
    this.toUInt()
        .toString(16)
        .let { hex ->
            when (hex.length) {
                5 -> "#0$hex"
                4 -> "#00$hex"
                3 -> "#000$hex"
                else -> "#$hex"
            }
        }.toUpperCase()

@ExperimentalUnsignedTypes
fun Int.toRgbString(): String =
    this.withAlpha(0)
        .toUInt()
        .toString(16)
        .let { hex ->
            when (hex.length) {
                5 -> "#0$hex"
                4 -> "#00$hex"
                3 -> "#000$hex"
                else -> "#$hex"
            }
        }.toUpperCase()