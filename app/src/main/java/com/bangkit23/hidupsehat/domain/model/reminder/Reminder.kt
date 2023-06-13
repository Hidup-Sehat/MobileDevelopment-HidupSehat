package com.bangkit23.hidupsehat.domain.model.reminder

data class Reminder(
    val id: Int = 0,
    val title: String,
    val time: Long,
    val type: Int,
    var isActive: Boolean = false,
)