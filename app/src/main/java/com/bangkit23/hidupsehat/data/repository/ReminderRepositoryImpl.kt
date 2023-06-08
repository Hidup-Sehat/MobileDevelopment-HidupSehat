package com.bangkit23.hidupsehat.data.repository

import com.bangkit23.hidupsehat.data.source.local.entity.ReminderEntity
import com.bangkit23.hidupsehat.data.source.local.room.ReminderDao
import com.bangkit23.hidupsehat.domain.model.reminder.Reminder
import com.bangkit23.hidupsehat.domain.reporitory.ReminderRepository
import com.bangkit23.hidupsehat.util.toDomain
import com.bangkit23.hidupsehat.util.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao,
) : ReminderRepository {

    override fun getReminders(type: Int) = flow {
        val reminders = reminderDao.getReminders(type)
            .map { it.map(ReminderEntity::toDomain) }
        emitAll(reminders)
    }.flowOn(Dispatchers.IO)

    override suspend fun updateReminder(reminder: Reminder) {
        reminderDao.upsertReminder(reminder.toEntity())
    }
}