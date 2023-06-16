package com.bangkit23.hidupsehat.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class UpdateStatisticRequest(

	@field:SerializedName("actualWater")
	val actualWater: Int = 0,

	@field:SerializedName("actualSleep")
	val actualSleep: Int = 0
)
