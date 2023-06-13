package com.bangkit23.hidupsehat.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bangkit23.hidupsehat.data.source.local.entity.FoodEntity
import com.bangkit23.hidupsehat.data.source.local.entity.ReminderEntity

@Database(
    entities = [FoodEntity::class, ReminderEntity::class],
    version = 1,
    exportSchema = true
)
abstract class HidupSehatDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun reminderDao(): ReminderDao
}