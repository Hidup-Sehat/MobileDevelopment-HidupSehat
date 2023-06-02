package com.bangkit23.hidupsehat.domain.usecase.user

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
}