package com.bangkit23.hidupsehat.presentation.screen.diary

data class DiaryState(
    val emotionPositive : String? = null,
    val emotionNegative : String? = null,
    val source : String? = null,
    val note : String? = "",
    val isDiaryDone : Boolean = false,
    val diaryError : String? = null,
    val isLoading: Boolean = false,
)
