@file:JvmName("CollectionExt")
package com.olekdia.common.extensions

import kotlin.jvm.JvmName

inline fun <T> Iterable<T>.sumBy(selector: (T) -> Long): Long {
    var sum: Long = 0L
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

infix fun Set<*>?.contentEquals(other: Set<*>?): Boolean {
    return if (this == null || other == null) {
        this == null && other == null
    } else if (this.size != other.size) {
        false
    } else {
        for (item in this) {
            if (!other.contains(item)) return false
        }
        true
    }
}