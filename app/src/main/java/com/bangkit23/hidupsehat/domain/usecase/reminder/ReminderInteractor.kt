package com.bangkit23.hidupsehat.domain.usecase.reminder

import com.bangkit23.hidupsehat.domain.model.reminder.Reminder
import com.bangkit23.hidupsehat.domain.reporitory.ReminderRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderInteractor @Inject constructor(
    private val reminderRepository: ReminderRepository,
): ReminderUseCase {

    override fun getEatingReminders() =
        reminderRepository.getReminders(type = 0)

    override fun getActivityReminders() =
        reminderRepository.getReminders(type = 1)

    override suspend fun updateReminder(reminder: Reminder) {
        reminderRepository.updateReminder(reminder)
    }
}