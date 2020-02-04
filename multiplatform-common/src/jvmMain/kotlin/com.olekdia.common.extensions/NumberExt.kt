package com.olekdia.common.extensions

actual fun Int.numberOfTrailingZeros(): Int =
    Integer.numberOfTrailingZeros(this)