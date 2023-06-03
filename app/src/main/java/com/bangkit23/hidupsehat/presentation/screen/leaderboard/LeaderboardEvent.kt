package com.bangkit23.hidupsehat.presentation.screen.leaderboard

sealed class LeaderboardEvent {
    data class OnLeaderboardTypeChanged(val position: Int) : LeaderboardEvent()
}