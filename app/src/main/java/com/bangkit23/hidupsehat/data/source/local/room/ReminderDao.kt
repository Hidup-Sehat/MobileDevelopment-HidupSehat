package com.bangkit23.hidupsehat.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.bangkit23.hidupsehat.data.source.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Query("SELECT * FROM reminder_entity WHERE type = :type")
    fun getReminders(type: Int): Flow<List<ReminderEntity>>

    @Upsert
    suspend fun upsertReminder(reminderEntity: ReminderEntity)

    @Query("SELECT * FROM reminder_entity WHERE isActive = 1 ORDER BY time")
    fun getNearestActiveReminder(): Flow<List<ReminderEntity>>
}