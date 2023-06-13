package com.bangkit23.hidupsehat.domain.model.food

import com.google.gson.annotations.SerializedName

data class FoodHistoryDetailItem(

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

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("totalSerat")
    val totalFiber: Int? = null,

    @field:SerializedName("makanan")
    val foods: List<FoodsHistoryItem>
)

data class FoodsHistoryItem(

    @field:SerializedName("porsi")
    val portionSize: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("namaMakanan")
    val foodName: String? = null
)

