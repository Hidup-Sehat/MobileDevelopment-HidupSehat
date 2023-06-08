package com.bangkit23.hidupsehat.domain.usecase.reminder

import com.bangkit23.hidupsehat.domain.model.reminder.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderUseCase {
    fun getEatingReminders(): Flow<List<Reminder>>
    fun getActivityReminders(): Flow<List<Reminder>>
    suspend fun updateReminder(reminder: Reminder)
}