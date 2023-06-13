package com.bangkit23.hidupsehat.presentation.screen.monitoring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.food.FoodUseCase
import com.bangkit23.hidupsehat.presentation.screen.monitoring.model.Nutrition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonitoringViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MonitoringState())
    val state = _state.asStateFlow()

    init {
        getFoods()
        getNutrition()
    }

    fun onEvent(event: MonitoringEvent) {

    }

    private fun getFoods() = viewModelScope.launch {
        foodUseCase.searchFoods("Nasi").collect { foods ->
            _state.update {
                it.copy(
                    historyFoods = foods.take(5)
                )
            }
        }
    }

    private fun getNutrition() = viewModelScope.launch {
        val nutrition = listOf(
            Nutrition(
                id = 0,
                nutritionName = "Protein",
                total = 120,
                target = 200,
                remain = 80,
            ),
            Nutrition(
                id = 1,
                nutritionName = "Karbohidrat",
                total = 80,
                target = 140,
                remain = 60,
            ),
            Nutrition(
                id = 2,
                nutritionName = "Serat",
                total = 92,
                target = 340,
                remain = 248,
            ),
            Nutrition(
                id = 3,
                nutritionName = "Gula",
                total = 120,
                target = 200,
                remain = 80,
            ),
            Nutrition(
                id = 4,
                nutritionName = "Lemak",
                total = 234,
                target = 250,
                remain = 16,
            ),
            Nutrition(
                id = 5,
                nutritionName = "Kolestrol",
                total = 22,
                target = 55,
                remain = 33,
            ),
        )
        _state.update {
            it.copy(
                historyNutrition = nutrition
            )
        }
    }
}