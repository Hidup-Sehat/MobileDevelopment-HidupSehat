package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LeaderboardResponse(

	@field:SerializedName("data")
	val data: List<LeaderboardResponseItem>
)

data class LeaderboardResponseItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("points")
	val points: Int? = null
)
