package com.bangkit23.hidupsehat.domain.usecase.user

import com.bangkit23.hidupsehat.data.source.remote.request.UpdateStatisticRequest
import com.bangkit23.hidupsehat.domain.model.user.UserDetailRequestDto
import com.bangkit23.hidupsehat.domain.reporitory.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInteractor @Inject constructor(
    private val userRepository: UserRepository,
) : UserUseCase {

    override fun createUserDetail(userDetailRequestDto: UserDetailRequestDto) =
        userRepository.createUserDetail(userDetailRequestDto)

    override fun getUserNeeds() =
        userRepository.getUserNeeds()

    override fun addUserPoints(points: Int) =
        userRepository.addUserPoints(points)

    override fun updateUserStatistic(updateStatisticRequest: UpdateStatisticRequest) =
        userRepository.updateUserStatistic(updateStatisticRequest)
}