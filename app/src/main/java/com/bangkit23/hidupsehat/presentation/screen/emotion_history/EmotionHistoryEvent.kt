package com.bangkit23.hidupsehat.presentation.screen.emotion_history

sealed class EmotionHistoryEvent {
    data class OnGetDiaryByDate(
        val date : String
    ) : EmotionHistoryEvent()
}
