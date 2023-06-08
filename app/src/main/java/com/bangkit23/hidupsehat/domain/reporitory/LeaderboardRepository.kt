package com.bangkit23.hidupsehat.domain.reporitory

import com.bangkit23.hidupsehat.domain.model.leaderboard.Leaderboard
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface LeaderboardRepository {
    fun getLeaderboard(): Flow<Result<Leaderboard>>
}