package com.bangkit23.hidupsehat.presentation.screen.leaderboard.model

data class Leaderboard(
    val id: Int,
    val username: String,
    val name: String,
    val photoUrl: String,
    val position: Int,
    val points: Long,
)
