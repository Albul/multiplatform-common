package com.olekdia.common.extensions

actual fun Int.numberOfTrailingZeros(): Int =
    if (this == 0) {
        32
    } else {
        var v = this
        var n = 31
        var y = v shl 16
        if (y != 0) {
            n -= 16
            v = y
        }
        y = v shl 8
        if (y != 0) {
            n -= 8
            v = y
        }
        y = v shl 4
        if (y != 0) {
            n -= 4
            v = y
        }
        y = v shl 2
        if (y != 0) {
            n -= 2
            v = y
        }
        n - (v shl 1 ushr 31)
    }