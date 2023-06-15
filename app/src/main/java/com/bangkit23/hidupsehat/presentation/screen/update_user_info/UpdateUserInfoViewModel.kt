package com.bangkit23.hidupsehat.presentation.screen.update_user_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.data.source.remote.request.UpdateStatisticRequest
import com.bangkit23.hidupsehat.domain.usecase.user.UserUseCase
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateUserInfoViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UpdateUserInfoState())
    val state = _state.asStateFlow()

    fun onEvent(event: UpdateUserInfoEvent) {
        when (event) {
            is UpdateUserInfoEvent.SaveInitialInfo -> {
                _state.update {
                    it.copy(
                        sleepNeeds = event.sleepNeeds,
                        waterNeeds = event.waterNeeds
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
                saveUserInfo(event.sleepNeeds, event.waterNeeds)
            }
            is UpdateUserInfoEvent.ResetState -> {
                _state.update {
                    UpdateUserInfoState()
                }
            }
        }
    }

    private fun saveUserInfo(sleepNeeds: Int, waterNeeds: Int) = viewModelScope.launch {
        val request = UpdateStatisticRequest(
            actualSleep = sleepNeeds,
            actualWater = waterNeeds
        )
        userUseCase.updateUserStatistic(request).collect { result ->
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
                    _state.update {
                        it.copy(
                            isSuccess = true,
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }
}