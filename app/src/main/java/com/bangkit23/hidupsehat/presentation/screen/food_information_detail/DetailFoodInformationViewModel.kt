package com.bangkit23.hidupsehat.presentation.screen.food_information_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.domain.usecase.food.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFoodInformationViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase
) : ViewModel(){
    private val _state = MutableStateFlow(DetailFoodInformationState(

    ))
    val state = _state.asStateFlow()

    fun onEvent(event : DetailFoodInformationEvent){
        when(event){
            is DetailFoodInformationEvent.OnGetFoodById -> {
                getFoodByName(event.name)
            }

            is DetailFoodInformationEvent.OnDropDownItemClick -> {

            }
            is DetailFoodInformationEvent.OnPortionSizeClick -> {
                _state.update {
                    it.copy(
                        portionSize = emptyList()
                    )
                }
                event.food?.let { getPortionSizes(it) }

            }
        }
    }

    private fun getFoodByName(name : String){
        viewModelScope.launch {
            foodUseCase.getFoodByName(name)
                .collect {food ->
                    _state.update {
                        it.copy(
                            food = food
                        )
                    }

                }
        }
    }
    private fun getPortionSizes(food: Food) {
        viewModelScope.launch {
            foodUseCase.getFoodsPortionSize(food.name.toString())
                .collect { foods ->
                    _state.update {
                        it.copy(
                            portionSize = foods
                        )
                    }
                }
        }
    }
}