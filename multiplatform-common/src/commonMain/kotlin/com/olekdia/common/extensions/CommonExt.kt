@file:JvmName("CommonExt")

package com.olekdia.common.extensions

import kotlin.jvm.JvmName

//--------------------------------------------------------------------------------------------------
// ifNotNull
//--------------------------------------------------------------------------------------------------

inline fun <T, R> T?.ifNotNull(block: T.() -> R) {
    if (this != null) block()
}

inline fun <A, R> ifNotNull(a: A?, code: (A) -> R): R? =
    if (a != null) code(a) else null

inline fun <A, B, R> ifNotNull(a: A?, b: B?, code: (A, B) -> R): R? =
    if (a != null
        && b != null
    ) {
        code(a, b)
    } else {
        null
    }

inline fun <A, B, C, R> ifNotNull(a: A?, b: B?, c: C?, code: (A, B, C) -> R): R? =
    if (a != null
        && b != null
        && c != null
    ) {
        code(a, b, c)
    } else {
        null
    }

inline fun <A, B, C, D, R> ifNotNull(a: A?, b: B?, c: C?, d: D?, code: (A, B, C, D) -> R): R? =
    if (a != null
        && b != null
        && c != null
        && d != null
    ) {
        code(a, b, c, d)
    } else {
        null
    }

//--------------------------------------------------------------------------------------------------
// ifNotNullAnd
//--------------------------------------------------------------------------------------------------

inline fun <A, R> ifNotNullAnd(
    a: A?, exp1: Boolean, code: (A) -> R
): R? =
    if (a != null
        && exp1
    ) {
        code(a)
    } else {
        null
    }

inline fun <A, B, R> ifNotNullAnd(
    a: A?,
    b: B?,
    exp1: Boolean,
    code: (A, B) -> R
): R? =
    if (a != null
        && b != null
        && exp1
    ) {
        code(a, b)
    } else {
        null
    }

inline fun <A, B, C, R> ifNotNullAnd(
    a: A?,
    b: B?,
    c: C?,
    exp1: Boolean,
    code: (A, B, C) -> R
): R? =
    if (a != null
        && b != null
        && c != null
        && exp1
    ) {
        code(a, b, c)
    } else {
        null
    }

inline fun <A, R> ifNotNullAnd(
    a: A?, exp1: Boolean, exp2: Boolean, code: (A) -> R
): R? =
    if (a != null
        && exp1
        && exp2
    ) {
        code(a)
    } else {
        null
    }

//--------------------------------------------------------------------------------------------------
// let
//--------------------------------------------------------------------------------------------------

inline fun <A, B, R> let(a: A?, b: B?, code: (A?, B?) -> R?): R? =
    code(a, b)

inline fun <A, B, C, R> let(a: A?, b: B?, c: C?, code: (A?, B?, C?) -> R?): R? =
    code(a, b, c)

inline fun <A, B, C, D, R> let(a: A?, b: B?, c: C?, d: D?, code: (A?, B?, C?, D?) -> R?): R? =
    code(a, b, c, d)

//--------------------------------------------------------------------------------------------------

inline fun <A, R> ifNotNullElse(a: A?, ifCode: (A) -> R, elseCode: () -> R): R? =
    if (a != null) {
        ifCode(a)
    } else {
        elseCode()
    }

inline fun <A, B, R> ifNotNullElse(a: A?, b: B?, ifCode: (A, B) -> R, elseCode: () -> R): R? =
    if (a != null
        && b != null
    ) {
        ifCode(a, b)
    } else {
        elseCode()
    }

inline fun <T> T?.ifNotNullOrFalse(block: T.() -> Boolean): Boolean =
    if (this == null) false else block()

inline fun Boolean?.ifFalse(block: () -> Unit) {
    if (this == false) block()
}

inline fun Boolean?.ifTrue(block: () -> Unit) {
    if (this == true) block()
}

inline fun <T, R> T?.ifNull(block: T.() -> R) {
    if (this == null) block()
}