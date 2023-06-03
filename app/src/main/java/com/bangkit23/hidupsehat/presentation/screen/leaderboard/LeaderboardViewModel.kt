package com.bangkit23.hidupsehat.presentation.screen.leaderboard

import androidx.lifecycle.ViewModel
import com.bangkit23.hidupsehat.presentation.screen.leaderboard.model.Leaderboard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(LeaderboardState())
    val state = _state.asStateFlow()

    fun onEvent(event: LeaderboardEvent) {
        when (event) {
            is LeaderboardEvent.OnLeaderboardTypeChanged -> {
                when (event.position) {
                    0 -> getDailyLeaderboard()
                    1 -> getWeeklyLeaderboard()
                    2 -> getMonthlyLeaderboard()
                }
            }
        }
    }

    private fun getDailyLeaderboard() {
        _state.update {
            it.copy(
                listLeaderboard = listLeaderboard
            )
        }
    }

    private fun getWeeklyLeaderboard() {
        _state.update {
            it.copy(
                listLeaderboard = listLeaderboard.shuffled()
            )
        }
    }

    private fun getMonthlyLeaderboard() {
    }

    private val listLeaderboard = listOf(
        Leaderboard(
            id = 0,
            username = "@yusuf12",
            name = "Yusuf",
            photoUrl = "https://cdn-icons-png.flaticon.com/128/4140/4140061.png",
            position = 1,
            points = 89964
        ),
        Leaderboard(
            id = 1,
            username = "@fajar45",
            name = "Fajarudin",
            photoUrl = "https://cdn-icons-png.flaticon.com/128/4140/4140037.png",
            position = 2,
            points = 68578
        ),
        Leaderboard(
            id = 2,
            username = "@ja_ki",
            name = "Jaki Anwar",
            photoUrl = "https://cdn-icons-png.flaticon.com/128/706/706807.png",
            position = 3,
            points = 8733
        ),
        Leaderboard(
            id = 3,
            username = "@udin66",
            name = "Kamaludin",
            photoUrl = "https://cdn-icons-png.flaticon.com/128/4139/4139981.png",
            position = 4,
            points = 3455
        ),
        Leaderboard(
            id = 4,
            username = "@firmansyah_90",
            name = "Firmansyah",
            photoUrl = "https://cdn-icons-png.flaticon.com/128/219/219970.png",
            position = 5,
            points = 2564
        ),
    )
}