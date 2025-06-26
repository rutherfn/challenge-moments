package com.nicholas.rutherford.moments

fun String.firstTwoChars(): String {
    return if (this.length >= 2) {
        this.substring(0, 2).uppercase()
    } else {
        this
    }
}