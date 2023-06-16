package com.bangkit23.hidupsehat.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserNeedsResponse(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("calorieBurned")
	val burnedCalorie: Int? = null,

	@field:SerializedName("sleepNeeds")
	val sleepNeeds: Int? = null,

	@field:SerializedName("registeredAt")
	val registeredAt: String? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("dateOfBirth")
	val dateOfBirth: String? = null,

	@field:SerializedName("target")
	val target: String? = null,

	@field:SerializedName("weightTarget")
	val weightTarget: Int? = null,

	@field:SerializedName("actualSleep")
	val actualSleep: Int? = null,

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("actualWater")
	val actualWater: Int? = null,

	@field:SerializedName("waterNeeds")
	val waterNeeds: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("contactNumber")
	val contactNumber: String? = null,

	@field:SerializedName("calorieNeeds")
	val calorieNeeds: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("actualCalorie")
	val actualCalorie: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("age")
	val age: Int? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)
