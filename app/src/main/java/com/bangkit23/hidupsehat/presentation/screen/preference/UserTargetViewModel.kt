package com.bangkit23.hidupsehat.presentation.screen.preference

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserTargetViewModel @Inject constructor() : ViewModel() {

    var weightTarget = mutableStateOf("")
        private set

    var chosenTargetId = mutableStateOf(-1)
        private set

    fun setWeightTarget(target: String) {
        weightTarget.value = target
    }

    fun setChosenTargetId(targetId: Int) {
        chosenTargetId.value = targetId
    }
}