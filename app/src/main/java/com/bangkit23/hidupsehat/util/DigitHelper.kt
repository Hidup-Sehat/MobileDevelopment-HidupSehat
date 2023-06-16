package com.bangkit23.hidupsehat.util

import java.text.DecimalFormat

fun Double?.get3DigitsOnly(): String {
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(this)
}