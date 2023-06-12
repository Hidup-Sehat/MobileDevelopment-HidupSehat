package com.bangkit23.hidupsehat.presentation.screen.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.leaderboard.LeaderboardItem
import com.bangkit23.hidupsehat.domain.model.leaderboard.LeaderboardType
import com.bangkit23.hidupsehat.domain.usecase.leaderboard.LeaderboardUseCase
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val leaderboardUseCase: LeaderboardUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LeaderboardState())
    val state = _state.asStateFlow()

    fun onEvent(event: LeaderboardEvent) {
        when (event) {
            is LeaderboardEvent.OnLeaderboardTypeChanged -> {
                when (event.position) {
                    0 -> getLeaderboard(LeaderboardType.DAILY)
                    1 -> getLeaderboard(LeaderboardType.WEEKLY)
                    2 -> getLeaderboard(LeaderboardType.MONTHLY)
                }
            }
        }
    }

    private fun getLeaderboard(type: LeaderboardType) = viewModelScope.launch {
        leaderboardUseCase.getLeaderboard(type).collect { result ->
            when (result) {
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
                            isLoading = false,
                            listLeaderboard = result.data.leaderboards,
                            userPosition = result.data.userPosition ?: LeaderboardItem()
                        )
                    }
                }

                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }
}