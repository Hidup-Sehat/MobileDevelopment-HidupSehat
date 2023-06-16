package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AddFoodsResponse(

	@field:SerializedName("data")
	val data: AddFoodsData,

	@field:SerializedName("message")
	val message: String? = null
)

data class AddFoodsData(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("lastUpdated")
	val lastUpdated: String? = null,

	@field:SerializedName("totalKarbohidrat")
	val totalCarb: Int? = null,

	@field:SerializedName("totalProtein")
	val totalProtein: Int? = null,

	@field:SerializedName("totalLemak")
	val totalFat: Int? = null,

	@field:SerializedName("totalSerat")
	val totalFiber: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("makanan")
	val foods: List<AddFoodsItem>
)

data class AddFoodsItem(

	@field:SerializedName("porsi")
	val portionSize: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("namaMakanan")
	val foodName: String? = null,

	@field:SerializedName("kal")
	val calorie: Int? = null
)
