package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateStatisticResponse(

	@field:SerializedName("data")
	val data: UpdateStatisticResponseData,

	@field:SerializedName("message")
	val message: String? = null
)

data class UpdateStatisticResponseData(

	@field:SerializedName("actualWater")
	val actualWater: Int? = null,

	@field:SerializedName("actualSleep")
	val actualSleep: Int? = null
)
