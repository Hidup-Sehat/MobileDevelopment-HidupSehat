package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AddEmotionResponse(

	@field:SerializedName("data")
	val data: AddEmotionDataResponse?,

	@field:SerializedName("message")
	val message: String
)

data class AddEmotionDataResponse(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("lastUpdated")
	val lastUpdated: String,

	@field:SerializedName("note")
	val note: String,

	@field:SerializedName("emotionSource")
	val emotionSource: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("emotionPositive")
	val emotionPositive: String,

	@field:SerializedName("emotionNegative")
	val emotionNegative: String
)
