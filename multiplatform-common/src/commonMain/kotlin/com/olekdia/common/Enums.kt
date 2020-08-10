@file:JvmName("Enums")
package com.olekdia.common

import kotlin.jvm.JvmName

@Retention(AnnotationRetention.SOURCE)
annotation class NumeralSystem {
    companion object {
        const val WESTERN_ARABIC = 0
        const val EASTERN_ARABIC = 1
        const val PERSO_ARABIC = 2
        const val GUJARATI = 3
        const val DEVANAGARI = 4
        const val THAI = 5
    }
}