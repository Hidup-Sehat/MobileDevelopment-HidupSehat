package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ActivityResponse(

	@field:SerializedName("activity")
	val activity: List<ActivityResponseItem>
)

data class ActivityResponseItem(

	@field:SerializedName("difficulty")
	val difficulty: String? = null,

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("caloriesBurned")
	val caloriesBurned: Int? = null,

	@field:SerializedName("movementCount")
	val movementCount: Int? = null,

	@field:SerializedName("movementList")
	val movementList: List<MovementResponseItem>,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("cardColor")
	val cardColor: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)

data class MovementResponseItem(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("movementName")
	val movementName: String? = null,

	@field:SerializedName("movementDesc")
	val movementDesc: String? = null
)
