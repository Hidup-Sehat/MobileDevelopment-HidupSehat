package com.bangkit23.hidupsehat.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class AddEmotionRequest(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("lastUpdated")
	val lastUpdated: String,

	@field:SerializedName("note")
	val note: String,

	@field:SerializedName("emotionSource")
	val emotionSource: String,

	@field:SerializedName("emotionPositive")
	val emotionPositive: String,

	@field:SerializedName("emotionNegative")
	val emotionNegative: String
)
