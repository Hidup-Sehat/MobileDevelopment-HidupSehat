package com.bangkit23.hidupsehat.util

fun String.getCalories(count: Int = 1): Int {
    val foodCalorie = when (this) {
        "Tomato" -> 20
        "Salad" -> 313
        "Seafood" -> 84
        "Cheese" -> 113
        else -> 0
    }
    return foodCalorie * count
}