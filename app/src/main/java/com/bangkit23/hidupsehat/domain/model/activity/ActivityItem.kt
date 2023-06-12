package com.bangkit23.hidupsehat.domain.model.activity

data class ActivityItem(
    val difficulty: String? = null,
    val imgUrl: String? = null,
    val caloriesBurned: Int? = null,
    val movementCount: Int? = null,
    val movementList: List<MovementItem>,
    val id: String? = null,
    val cardColor: Int? = null,
    val type: String? = null,
    val category: String? = null
)

data class MovementItem(
    val id: String? = null,
    val imgUrl: String? = null,
    val movementName: String? = null,
    val movementDesc: String? = null,
    val movementData: BodyAngle,
)
