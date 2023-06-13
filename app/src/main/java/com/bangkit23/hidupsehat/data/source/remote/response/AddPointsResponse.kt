package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AddPointsResponse(

	@field:SerializedName("data")
	val data: AddPointsResponseData,

	@field:SerializedName("message")
	val message: String? = null
)

data class AddPointsResponseData(

	@field:SerializedName("points_added")
	val pointsAdded: Int? = null,

	@field:SerializedName("previous_points")
	val previousPoints: Int? = null,

	@field:SerializedName("points")
	val points: Int? = null
)
