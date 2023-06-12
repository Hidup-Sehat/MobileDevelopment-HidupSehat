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
	val movementDesc: String? = null,

	@field:SerializedName("movementData")
	val movementData: BodyAngleResponse
)

data class BodyAngleResponse(

	@field:SerializedName("sudutKetiakKanan")
	val sudutKetiakKanan: Int = 0,

	@field:SerializedName("sudutPahaKiri")
	val sudutPahaKiri: Int = 0,

	@field:SerializedName("sudutPinggulKanan")
	val sudutPinggulKanan: Int = 0,

	@field:SerializedName("sudutSikuKiri")
	val sudutSikuKiri: Int,

	@field:SerializedName("sudutPundakKanan")
	val sudutPundakKanan: Int,

	@field:SerializedName("sudutPinggulKiri")
	val sudutPinggulKiri: Int,

	@field:SerializedName("sudutLututKiri")
	val sudutLututKiri: Int,

	@field:SerializedName("sudutSikuKanan")
	val sudutSikuKanan: Int,

	@field:SerializedName("sudutLututKanan")
	val sudutLututKanan: Int,

	@field:SerializedName("sudutKetiakKiri")
	val sudutKetiakKiri: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("sudutPahaKanan")
	val sudutPahaKanan: Int,

	@field:SerializedName("sudutPundakKiri")
	val sudutPundakKiri: Int
)
