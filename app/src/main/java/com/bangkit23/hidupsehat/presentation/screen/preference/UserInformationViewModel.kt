package com.bangkit23.hidupsehat.presentation.screen.preference

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInformationViewModel @Inject constructor() : ViewModel() {

    var selectedGenderId = mutableStateOf(-1)
        private set

    var age = mutableStateOf("")
        private set

    var height = mutableStateOf("")
        private set

    var currentWeight = mutableStateOf("")
        private set

    fun setSelectedGenderId(genderId: Int) {
        selectedGenderId.value = genderId
    }

    fun setUserAge(userAge: String) {
        age.value = userAge
    }

    fun setUserHeight(userHeight: String) {
        height.value = userHeight
    }

    fun setCurrentWeight(userWeight: String) {
        currentWeight.value = userWeight
    }
}