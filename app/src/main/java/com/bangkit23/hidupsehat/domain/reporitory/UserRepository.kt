package com.bangkit23.hidupsehat.domain.reporitory

import com.bangkit23.hidupsehat.data.source.remote.request.UpdateStatisticRequest
import com.bangkit23.hidupsehat.domain.model.user.AddPoints
import com.bangkit23.hidupsehat.domain.model.user.UserDetail
import com.bangkit23.hidupsehat.domain.model.user.UserDetailRequestDto
import com.bangkit23.hidupsehat.domain.model.user.UserNeeds
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun createUserDetail(userDetailRequestDto: UserDetailRequestDto): Flow<Result<UserDetail>>
    fun getUserNeeds(): Flow<Result<UserNeeds>>
    fun addUserPoints(points: Int): Flow<Result<AddPoints>>
    fun updateUserStatistic(updateStatisticRequest: UpdateStatisticRequest): Flow<Result<Boolean>>
}