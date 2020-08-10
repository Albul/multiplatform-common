@file:JvmName("Constants")
package com.olekdia.common

import kotlin.jvm.JvmName

const val INVALID: Int = -1
const val INVALID_F: Float = -1F
const val INVALID_D: Double = -1.0
const val INVALID_L: Long = -1L

const val TRUE: Int = 1
const val FALSE: Int = 0

const val FIRST_GREATER = 1
const val EQUALS = 0
const val SECOND_GREATER = -1

//--------------- Mime

const val ANY_MIME = "*/*"
const val TEXT_MIME = "text/plain"
const val HTML_MIME = "text/html"
const val CSV_MIME = "text/csv"
const val RFC_MIME = "message/rfc822"
const val SQLITE_MIME = "application/x-sqlite3"
const val SQLITE3_MIME = "application/vnd.sqlite3"

const val SCHEME_HTTP = "http"
const val SCHEME_HTTPS = "https"
const val UTF_8 = "UTF-8"

//--------------- Math

const val ZERO_DIGIT = '0'
const val NINE_DIGIT = '9'

/**
 * 2*PI in degrees
 */
const val DEG_2PI = 360F
/**
 * Float PI value
 */
const val PI = 3.141592653589793F
/**
 * Float golden ratio value
 */
const val GOLDEN_RATIO = 1.61803F

//--------------- Bidi

/**
 * Unicode "Left-To-Right Isolate"
 * Sets base direction to LTR and isolates the embedded content from the surrounding text
 */
const val LRI = '\u2066'
/**
 * Unicode "Right-To-Left Isolate"
 * Sets base direction to RTL and isolates the embedded content from the surrounding text
 */
const val RLI = '\u2067'
/**
 * Unicode "First-Strong Isolate"
 * Isolates the content and sets the direction according to the first strongly typed directional character
 */
const val FSI = '\u2068'
/**
 * Unicode "Left-To-Right Embedding"
 * Sets base direction to LTR but allows embedded text to interact with surrounding content,
 * so risk of spillover effects
 */
const val LRE = '\u202A'
/**
 * Unicode "Right-To-Left Embedding"
 * Sets base direction to RTL but allows embedded text to interact with surrounding content,
 * so risk of spillover effects
 */
const val RLE = '\u202B'
/**
 * Unicode "Left-To-Right Override"
 * Overrides the bidirectional algorithm to display characters in memory order,
 * progressing from left to right
 */
const val LRO = '\u202D'
/**
 * Unicode "Right-To-Left Override"
 * Overrides the bidirectional algorithm to display characters in memory order,
 * progressing from right to left
 */
const val RLO = '\u202E'
/**
 * Unicode "Pop Directional Formatting"
 * Used for RLE or LRE, RLO or LRO
 */
const val PDF = '\u202C'
/**
 * Unicode "Pop Directional Isolate"
 * Used for RLI, LRI or FSI
 */
const val PDI = '\u2069'
/**
 * Unicode "Left-To-Right Mark"
 * Strongly typed LTR character
 */
const val LRM = '\u200E'
/**
 * Unicode "Right-To-Left Mark"
 * Strongly typed RTL character
 */
const val RLM = '\u200F'