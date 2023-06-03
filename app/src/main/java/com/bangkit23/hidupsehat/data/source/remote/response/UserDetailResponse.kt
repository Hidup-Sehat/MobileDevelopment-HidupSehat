package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(

	@field:SerializedName("data")
	val data: UserDetailResult? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class UserDetailResult(

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("currentWeight")
	val currentWeight: Int? = null,

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("targetWeight")
	val targetWeight: Int? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("targetUser")
	val targetUser: Int? = null,

	@field:SerializedName("age")
	val age: Int? = null,

	@field:SerializedName("height")
	val height: Int? = null
)
