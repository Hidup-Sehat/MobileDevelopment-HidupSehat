package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FeedDetailResponse(

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("title")
	val title: String
)
