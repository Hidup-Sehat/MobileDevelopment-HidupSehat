package com.bangkit23.hidupsehat.presentation.common

import androidx.lifecycle.ViewModel
import com.bangkit23.hidupsehat.domain.model.food.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FoodSharedViewModel @Inject constructor() : ViewModel(){
    private val _food = MutableStateFlow(
        Food(1)
    )
    val food = _food.asStateFlow()

    fun setFood(food : Food){
        _food.value = food
    }
}