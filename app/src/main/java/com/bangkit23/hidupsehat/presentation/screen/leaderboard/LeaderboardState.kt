package com.bangkit23.hidupsehat.presentation.screen.leaderboard

import com.bangkit23.hidupsehat.presentation.screen.leaderboard.model.Leaderboard

data class LeaderboardState(
    val listLeaderboard: List<Leaderboard> = emptyList(),
)