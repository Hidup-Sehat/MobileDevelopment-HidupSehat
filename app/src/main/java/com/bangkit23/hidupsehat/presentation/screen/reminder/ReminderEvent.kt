package com.bangkit23.hidupsehat.presentation.screen.reminder

import com.bangkit23.hidupsehat.domain.model.reminder.Reminder

sealed class ReminderEvent {
    data class OnSwitchCheckedChanged(
        val isActive: Boolean,
        val reminder: Reminder,
    ) : ReminderEvent()
    data class OnItemClick(val reminder: Reminder) : ReminderEvent()
    data class UpdateReminder(val reminder: Reminder) : ReminderEvent()
}