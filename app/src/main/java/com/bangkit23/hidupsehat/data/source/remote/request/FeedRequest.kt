package com.bangkit23.hidupsehat.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class FeedRequest(

	@field:SerializedName("user_uid")
	val userUid: String
)
