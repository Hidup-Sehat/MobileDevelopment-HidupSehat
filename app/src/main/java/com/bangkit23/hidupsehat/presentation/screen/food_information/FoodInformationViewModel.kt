package com.bangkit23.hidupsehat.presentation.screen.food_information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.food.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodInformationViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(FoodInformationState())
    val state = _state.asStateFlow()

    fun onEvent(event: FoodInformationEvent) {
        when (event) {
            is FoodInformationEvent.OnGetAllFeed -> {
                getAllFoods()
            }
        }
    }

    private fun getAllFoods() {
        viewModelScope.launch {
            foodUseCase.getAllFoods()
                .collect { foods ->
                    val shuffledFoods = foods.shuffled()
                    val selectedFoods = shuffledFoods.take(20)
                    _state.update {
                        it.copy(
                            foods = selectedFoods
                        )
                    }
                }
        }
    }



}