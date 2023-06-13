package com.bangkit23.hidupsehat.presentation.screen.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.domain.model.user.UserNeeds
import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData
import com.bangkit23.hidupsehat.presentation.screen.home.model.Feel

data class HomeState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val chosenEmotion: Feel? = null,
    val userNeeds: UserNeeds = UserNeeds(),
    val userData: UserData? = null,
    val manualFoods: SnapshotStateList<Food> = mutableStateListOf()
)