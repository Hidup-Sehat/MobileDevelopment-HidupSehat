package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LeaderboardResponse(

	@field:SerializedName("data")
	val data: List<LeaderboardItemResponse>
)

data class LeaderboardItemResponse(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("point")
	val point: Int? = null,

	@field:SerializedName("user_uid")
	val userUid: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("name")
	val name: String? = null,
)