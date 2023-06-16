package com.bangkit23.hidupsehat.presentation.screen.leaderboard

import com.bangkit23.hidupsehat.domain.model.leaderboard.LeaderboardItem

data class LeaderboardState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val listLeaderboard: List<LeaderboardItem> = emptyList(),
    val userPosition: LeaderboardItem = LeaderboardItem()
)