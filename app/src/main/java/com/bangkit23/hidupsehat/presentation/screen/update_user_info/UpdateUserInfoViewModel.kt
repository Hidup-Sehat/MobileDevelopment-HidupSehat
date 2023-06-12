package com.bangkit23.hidupsehat.presentation.screen.update_user_info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UpdateUserInfoViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(UpdateUserInfoState())
    val state = _state.asStateFlow()

    fun onEvent(event: UpdateUserInfoEvent) {
        when (event) {
            is UpdateUserInfoEvent.SaveInitialInfo -> {
                _state.update {
                    it.copy(
                        calorieNeeds = event.calorieNeeds,
                        calorieBurned = event.calorieBurned
                    )
                }
            }
            is UpdateUserInfoEvent.OnCalorieNeedsChanged -> {
                _state.update {
                    it.copy(
                        calorieNeeds = event.calorieNeeds
                    )
                }
            }
            is UpdateUserInfoEvent.OnCalorieBurnedChanged -> {
                _state.update {
                    it.copy(
                        calorieBurned = event.calorieBurned
                    )
                }
            }
            is UpdateUserInfoEvent.OnWaterNeedsChanged -> {
                _state.update {
                    it.copy(
                        waterNeeds = event.waterNeeds
                    )
                }
            }
            is UpdateUserInfoEvent.OnSleepNeedsChanged -> {
                _state.update {
                    it.copy(
                        sleepNeeds = event.sleepNeeds
                    )
                }
            }
            is UpdateUserInfoEvent.SaveUpdatedInfo -> {
                //Save To Server
            }
            is UpdateUserInfoEvent.ResetState -> {
                _state.update {
                    UpdateUserInfoState()
                }
            }
        }
    }
}