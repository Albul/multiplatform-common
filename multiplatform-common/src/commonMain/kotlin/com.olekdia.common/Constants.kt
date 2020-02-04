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

// Mime
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

// Math
const val DEG_2PI = 360F
const val PI = 3.141592653589793F
const val GOLDEN_RATIO = 1.61803F

// Bidi

/**
 * Unicode "Left-To-Right Embedding"
 */
const val LRE = '\u202A'
/**
 * Unicode "Right-To-Left Embedding"
 */
const val RLE = '\u202B'
/**
 * Unicode "Pop Directional Formatting"
 */
const val PDF = '\u202C'
/**
 * Unicode "Pop Directional Isolate"
 */
const val PDI = '\u2069'
/**
 * Left-To-Right Mark
 */
const val LRM = '\u200E'
/**
 * Right-To-Left Mark
 */
const val RLM = '\u200F'
/**
 * Left-To-Right Isolated
 */
const val LRI = '\u2066'
/**
 * Right-To-Left Isolated
 */
const val RLI = '\u2067'
/**
 * First-Strong Isolated
 */
const val FSI = '\u2068'

const val LRO = '\u202D'

const val RLO = '\u202E'