package com.bangkit23.hidupsehat.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class UpdateBurnedCalorieRequest(
    @field:SerializedName("calorieBurned")
    val burnedCalorie: Int
)
