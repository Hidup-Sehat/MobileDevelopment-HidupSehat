package com.bangkit23.hidupsehat.presentation.screen.exercise.yoga

import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise

sealed class YogaEvent {
    object Refresh : YogaEvent()
    data class OpenSheet(val exercise: Exercise) : YogaEvent()
    object DismissSheet : YogaEvent()
}
