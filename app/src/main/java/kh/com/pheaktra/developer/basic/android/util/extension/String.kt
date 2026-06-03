package kh.com.pheaktra.developer.basic.android.util.extension

import kh.com.pheaktra.developer.basic.android.common.ValueYN

fun String.isYes(): Boolean {
    return this == ValueYN.YES.value
}

fun String.isNo(): Boolean {
    return this == ValueYN.NO.value
}