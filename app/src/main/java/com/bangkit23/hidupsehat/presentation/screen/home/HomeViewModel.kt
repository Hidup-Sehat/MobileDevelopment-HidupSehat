package com.bangkit23.hidupsehat.presentation.screen.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bangkit23.hidupsehat.presentation.model.Food
import com.bangkit23.hidupsehat.presentation.screen.home.model.Feel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    var chosenEmotion = mutableStateOf<Feel?>(null)
        private set

    var foods = mutableStateListOf<Food>()
        private set

    fun setEmotion(emotionId: Feel?) {
        chosenEmotion.value = emotionId
    }

    fun addFood(food: Food) {
        foods.add(food)
    }
}