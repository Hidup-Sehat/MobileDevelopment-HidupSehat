package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FeedResponse(

	@field:SerializedName("data")
	val data: List<FeedResult>,

	@field:SerializedName("totalRecommendations")
	val totalRecommendations: Int,

	@field:SerializedName("limit")
	val limit: Int,

	@field:SerializedName("totalPages")
	val totalPages: Int,

	@field:SerializedName("page")
	val page: Int
)

data class FeedResult(

	@field:SerializedName("summary")
	val summary: String,

	@field:SerializedName("imgUrl")
	val imgUrl: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("key")
	val key: String
)
