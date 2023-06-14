package com.bangkit23.hidupsehat.presentation.screen.diary

sealed class DiaryEvent {
    data class onSaveDiary(
        val positive : String,
        val negative : String,
        val source : String,
        val note : String,
    ) : DiaryEvent()

    data class OnNoteChanged(val note : String) : DiaryEvent()
}
