package com.bangkit23.hidupsehat.domain.usecase.leaderboard

import com.bangkit23.hidupsehat.domain.model.leaderboard.LeaderboardType
import com.bangkit23.hidupsehat.domain.reporitory.LeaderboardRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaderboardInteractor @Inject constructor(
    private val leaderboardRepository: LeaderboardRepository,
) : LeaderboardUseCase {

    override fun getLeaderboard(type: LeaderboardType) = leaderboardRepository.getLeaderboard(type)
}