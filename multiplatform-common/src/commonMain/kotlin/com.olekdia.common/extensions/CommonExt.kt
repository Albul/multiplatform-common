@file:JvmName("CommonExt")
package com.olekdia.common.extensions

import kotlin.jvm.JvmName

inline fun <T, R> T?.ifNotNull(block: T.() -> R) {
    if (this != null) block()
}

inline fun <A, R> ifNotNull(a: A?, code: (A) -> R): R? {
    return if (a != null) code(a) else null
}

inline fun <A, B, R> ifNotNull(a: A?, b: B?, code: (A, B) -> R): R? {
    return if (a != null
        && b != null
    ) {
        code(a, b)
    } else {
        null
    }
}

inline fun <A, B, C, R> ifNotNull(a: A?, b: B?, c: C?, code: (A, B, C) -> R): R? {
    return if (a != null
        && b != null
        && c != null
    ) {
        code(a, b, c)
    } else {
        null
    }
}

inline fun <T, R> T?.ifNull(block: T.() -> R) {
    if (this == null) block()
}

inline fun <A, R> ifNotNullAnd(
    a: A?, exp1: Boolean, code: (A) -> R
): R? {
    return if (a != null
        && exp1
    ) {
        code(a)
    } else {
        null
    }
}

inline fun <A, B, R> ifNotNullAnd(
    a: A?,
    b: B?,
    exp1: Boolean,
    code: (A, B) -> R
): R? {
    return if (a != null
        && b != null
        && exp1
    ) {
        code(a, b)
    } else {
        null
    }
}

inline fun <A, B, C, R> ifNotNullAnd(
    a: A?,
    b: B?,
    c: C?,
    exp1: Boolean,
    code: (A, B, C) -> R
): R? {
    return if (a != null
        && b != null
        && c != null
        && exp1
    ) {
        code(a, b, c)
    } else {
        null
    }
}

inline fun <A, R> ifNotNullAnd(
    a: A?, exp1: Boolean, exp2: Boolean, code: (A) -> R
): R? {
    return if (a != null
        && exp1
        && exp2
    ) {
        code(a)
    } else {
        null
    }
}

inline fun <A, B, R> ifNotNullElse(a: A?, b: B?, ifCode: (A, B) -> R, elseCode: () -> R): R? {
    return if (a != null
        && b != null
    ) {
        ifCode(a, b)
    } else {
        elseCode()
    }
}

inline fun <T> T?.ifNotNullOrFalse(block: T.() -> Boolean): Boolean {
    return if (this == null) false else block()
}

inline fun Boolean?.ifFalse(block: () -> Unit) {
    if (this == false) block()
}

inline fun Boolean?.ifTrue(block: () -> Unit) {
    if (this == true) block()
}