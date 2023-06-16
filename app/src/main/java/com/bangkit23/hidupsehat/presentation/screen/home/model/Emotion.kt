package com.bangkit23.hidupsehat.presentation.screen.home.model

data class Emotion(
    val feel: Feel,
    val emoticon: String,
    var isChosen: Boolean = false
)

enum class Feel(val value: String) {
    VERY_SAD("Very Sad"),
    SAD("Sad"),
    NEUTRAL("Neutral"),
    HAPPY("Happy"),
    VERY_HAPPY("Very Happy"),
}

val emotions = listOf(
    Emotion(feel = Feel.VERY_SAD, emoticon = "\uD83D\uDE2D"),
    Emotion(feel = Feel.SAD, emoticon = "\uD83D\uDE22"),
    Emotion(feel = Feel.NEUTRAL, emoticon = "\uD83D\uDE1F"),
    Emotion(feel = Feel.HAPPY, emoticon = "\uD83D\uDE42"),
    Emotion(feel = Feel.VERY_HAPPY, emoticon = "\uD83D\uDE01"),
)
