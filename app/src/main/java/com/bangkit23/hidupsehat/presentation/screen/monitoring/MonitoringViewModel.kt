package com.bangkit23.hidupsehat.presentation.screen.monitoring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.food.FoodHistoryDetailItem
import com.bangkit23.hidupsehat.domain.model.food.FoodsHistoryItem
import com.bangkit23.hidupsehat.domain.usecase.food.FoodUseCase
import com.bangkit23.hidupsehat.domain.usecase.monitoring.MonitoringUseCase
import com.bangkit23.hidupsehat.presentation.screen.monitoring.common.toFood
import com.bangkit23.hidupsehat.presentation.screen.monitoring.model.Nutrition
import com.bangkit23.hidupsehat.util.DateHelper
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonitoringViewModel @Inject constructor(
    private val monitoringUseCase: MonitoringUseCase,
    private val foodUseCase: FoodUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MonitoringState())
    val state = _state.asStateFlow()

    init {
        getFoods()
    }

    fun onEvent(event: MonitoringEvent) {

    }

    private fun getFoods() = viewModelScope.launch {
        monitoringUseCase.getFoodsHistory(DateHelper.getCurrentDate()).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }

                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }

                is Result.Success -> {
                    val foods = result.data.foods.map(FoodsHistoryItem::toFood)
                    val nutrition = result.data.getNutrition()

                    _state.update {
                        it.copy(
                            historyNutrition = nutrition,
                            historyFoods = foods
                        )
                    }
                }
            }
        }
    }

    private fun FoodHistoryDetailItem.getNutrition() = listOf(
        Nutrition(
            id = 0,
            nutritionName = "Protein",
            total = totalProtein ?: 0,
            target = 55,
            remain = 55 - (totalProtein ?: 0),
        ),
        Nutrition(
            id = 1,
            nutritionName = "Karbohidrat",
            total = totalCarb ?: 0,
            target = 300,
            remain = 300 - (totalCarb ?: 0),
        ),
        Nutrition(
            id = 2,
            nutritionName = "Serat",
            total = totalFiber ?: 0,
            target = 37,
            remain = 37 - (totalFiber ?: 0),
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
            total = totalFat ?: 0,
            target = 67,
            remain = 67 - (totalFat ?: 0),
        ),
        Nutrition(
            id = 5,
            nutritionName = "Kolestrol",
            total = 22,
            target = 55,
            remain = 33,
        ),
    )
}