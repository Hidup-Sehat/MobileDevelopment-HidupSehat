package com.bangkit23.hidupsehat.data.repository

import com.bangkit23.hidupsehat.data.source.firebase.FirebaseAuth
import com.bangkit23.hidupsehat.data.source.remote.RemoteDataSource
import com.bangkit23.hidupsehat.domain.model.leaderboard.Leaderboard
import com.bangkit23.hidupsehat.domain.model.leaderboard.LeaderboardItem
import com.bangkit23.hidupsehat.domain.reporitory.LeaderboardRepository
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaderboardRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val firebaseAuth: FirebaseAuth,
) : LeaderboardRepository {

    override fun getLeaderboard(): Flow<Result<Leaderboard>> = flow {
        emit(Result.Loading())
        try {
            val currentUser = firebaseAuth.getSignedUser()
            val response = remoteDataSource.getLeaderboard()
            val data = response.data
                .sortedByDescending { it.points }
                .mapIndexed { index, data ->
                    LeaderboardItem(
                        name = data.name,
                        username = "@${data.username}",
                        points = data.points,
                        position = index + 1,
                        avatar = if (data.name == currentUser?.username) currentUser?.profilePictureUrl else "https://cdn-icons-png.flaticon.com/128/4140/4140061.png"
                    )
                }
            val result = Leaderboard(
                leaderboards = data,
                userPosition = data.find { it.name == currentUser?.username }
                    ?.copy(avatar = currentUser?.profilePictureUrl)
            )
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }
}