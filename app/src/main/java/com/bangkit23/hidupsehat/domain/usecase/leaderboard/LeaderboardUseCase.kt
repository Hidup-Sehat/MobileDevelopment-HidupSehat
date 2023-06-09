package com.bangkit23.hidupsehat.domain.usecase.leaderboard

import com.bangkit23.hidupsehat.domain.model.leaderboard.Leaderboard
import com.bangkit23.hidupsehat.domain.model.leaderboard.LeaderboardType
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface LeaderboardUseCase {
    fun getLeaderboard(type: LeaderboardType): Flow<Result<Leaderboard>>
}