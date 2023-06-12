package com.bangkit23.hidupsehat.domain.model.leaderboard

data class Leaderboard(
    val userPosition: LeaderboardItem? = null,
    val leaderboards: List<LeaderboardItem>,
)

data class LeaderboardItem(
    val userId: String? = null,
    val name: String? = null,
    val username: String? = null,
    val points: Int? = null,
    val position: Int? = null,
    val avatar: String? = null,
)
