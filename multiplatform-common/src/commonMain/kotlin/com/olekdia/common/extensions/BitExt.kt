@file:JvmName("BitExt")
package com.olekdia.common.extensions

import kotlin.jvm.JvmName

// Zero based, 1_000 > 4 index
fun Int.toBitIndex(): Int = this.numberOfTrailingZeros()

fun Int.isBitEnabled(bit: Int): Boolean = this and bit > 0

fun Int.isBitIndexEnabled(bitIndex: Int): Boolean = this and (1 shl bitIndex) > 0

fun Int.addBit(bit: Int): Int = this or bit

fun Long.addBit(bit: Long): Long = this or bit

fun Int.subtractBit(bit: Int): Int = this and bit.inv()

fun Long.subtractBit(bit: Long): Long = this and bit.inv()

fun Int.addBitIndex(bitIndex: Int): Int = this or (1 shl bitIndex)

fun Long.addBitIndex(bitIndex: Int): Long = this or (1 shl bitIndex).toLong()

fun Int.subtractBitIndex(bitIndex: Int): Int = this and (1 shl bitIndex).inv()

fun Long.subtractBitIndex(bitIndex: Int): Long = this and (1 shl bitIndex).inv().toLong()

fun Int.addBitIndexes(vararg args: Int): Int {
    var bitSet = this
    for (bitIndex in args) {
        bitSet = bitSet.addBitIndex(bitIndex)
    }
    return bitSet
}

fun Int.subtractBitIndexes(vararg args: Int): Int {
    var bitSet = this
    for (bitIndex in args) {
        bitSet = bitSet.subtractBitIndex(bitIndex)
    }
    return bitSet
}