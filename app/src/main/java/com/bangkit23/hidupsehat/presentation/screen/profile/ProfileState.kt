package com.bangkit23.hidupsehat.presentation.screen.profile

import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData

data class ProfileState(
    val userData: UserData? = null,
    val isLoggedOut: Boolean = false,
)