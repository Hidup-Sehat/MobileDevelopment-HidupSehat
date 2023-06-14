package com.bangkit23.hidupsehat.domain.model.diary

data class Diary(
    val date: String,
    val lastUpdated: String,
    val note: String,
    val emotionSource: String,
    val id: String,
    val emotionPositive: String,
    val emotionNegative: String
)

