package com.bangkit23.hidupsehat.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder_entity")
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val time: Long,
    val type: Int,
    var isActive: Boolean = false,
)
