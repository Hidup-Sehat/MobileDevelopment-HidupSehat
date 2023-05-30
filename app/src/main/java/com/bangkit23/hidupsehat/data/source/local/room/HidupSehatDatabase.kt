package com.bangkit23.hidupsehat.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bangkit23.hidupsehat.data.source.local.entity.FoodEntity

@Database(
    entities = [FoodEntity::class],
    version = 1,
    exportSchema = true
)
abstract class HidupSehatDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
}