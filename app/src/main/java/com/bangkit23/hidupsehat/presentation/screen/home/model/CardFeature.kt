package com.bangkit23.hidupsehat.presentation.screen.home.model

import androidx.annotation.DrawableRes
import androidx.compose.material3.CardColors

data class CardFeature(
    val title: String,
    @DrawableRes val icon: Int,
    val cardColor: CardColors,
)