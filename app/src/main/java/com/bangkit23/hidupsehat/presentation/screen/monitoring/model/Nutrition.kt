package com.bangkit23.hidupsehat.presentation.screen.monitoring.model

data class Nutrition(
    val id: Int,
    val nutritionName: String,
    val total: Int,
    val target: Int,
    val remain: Int,
)