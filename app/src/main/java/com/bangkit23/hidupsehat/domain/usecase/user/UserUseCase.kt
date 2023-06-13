package com.bangkit23.hidupsehat.domain.usecase.user

import com.bangkit23.hidupsehat.domain.model.user.AddPoints
import com.bangkit23.hidupsehat.domain.model.user.UserDetail
import com.bangkit23.hidupsehat.domain.model.user.UserDetailRequestDto
import com.bangkit23.hidupsehat.domain.model.user.UserNeeds
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun createUserDetail(userDetailRequestDto: UserDetailRequestDto): Flow<Result<UserDetail>>
    fun getUserNeeds(): Flow<Result<UserNeeds>>
    fun addUserPoints(points: Int): Flow<Result<AddPoints>>
}