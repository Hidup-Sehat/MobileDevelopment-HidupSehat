package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateBurnedCalorieResponse(

	@field:SerializedName("data")
	val data: BurnedCalorieResponse,

	@field:SerializedName("message")
	val message: String? = null
)

data class BurnedCalorieResponse(

	@field:SerializedName("calorieBurned")
	val calorieBurned: Int? = null,

	@field:SerializedName("user_uid")
	val userUid: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
