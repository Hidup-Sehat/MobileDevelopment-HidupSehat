package com.bangkit23.hidupsehat.domain.reporitory

import com.bangkit23.hidupsehat.domain.model.reminder.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    fun getReminders(type: Int): Flow<List<Reminder>>
    suspend fun updateReminder(reminder: Reminder)
}