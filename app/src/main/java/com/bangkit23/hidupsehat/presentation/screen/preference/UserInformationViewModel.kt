package com.bangkit23.hidupsehat.presentation.screen.preference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.user.UserDetailRequestDto
import com.bangkit23.hidupsehat.domain.usecase.user.UserUseCase
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInformationViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserInformationState())
    val state = _state.asStateFlow()

    fun onEvent(event: UserInformationEvent) {
        when (event) {
            is UserInformationEvent.CreateUserDetailPreferences -> {
                val userDetailRequest = UserDetailRequestDto(
                    username = event.username,
                    name = event.name,
                    contactNumber = event.contactNumber,
                    dateOfBirth = "2001-09-09",
                    age = event.age,
                    gender = event.gender,
                    height = event.height,
                    weight = event.weight,
                    target = event.target,
                    weightTarget = event.weightTarget
                )
                createUserDetailPreferences(userDetailRequest)
            }
            is UserInformationEvent.OnAgeChanged -> {
                _state.update {
                    it.copy(
                        age = event.age
                    )
                }
            }
            is UserInformationEvent.OnCurrentWeightChanged -> {
                _state.update {
                    it.copy(
                        currentWeight = event.currentWeight
                    )
                }
            }
            is UserInformationEvent.OnGenderIdChanged -> {
                _state.update {
                    it.copy(
                        selectedGenderId = event.selectedGenderId
                    )
                }
            }
            is UserInformationEvent.OnHeightChanged -> {
                _state.update {
                    it.copy(
                        height = event.height
                    )
                }
            }
        }
    }

    private fun createUserDetailPreferences(
        userDetailRequest: UserDetailRequestDto
    ) = viewModelScope.launch {
        userUseCase.createUserDetail(userDetailRequest)
            .collect { result ->
                when (result) {
                    is Result.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                errorMessage = result.message
                            )
                        }
                    }
                    is Result.Loading -> {
                        _state.update {
                            it.copy(
                                loading = true
                            )
                        }
                    }
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                success = true
                            )
                        }
                    }
                }
            }
    }
}