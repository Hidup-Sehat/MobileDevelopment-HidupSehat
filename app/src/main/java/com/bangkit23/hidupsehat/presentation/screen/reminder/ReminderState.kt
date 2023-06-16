package com.bangkit23.hidupsehat.presentation.screen.reminder

import com.bangkit23.hidupsehat.domain.model.reminder.Reminder

data class ReminderState(
    val editedReminder: Reminder? = null,
    val eatReminders: List<Reminder> = emptyList(),
    val activityReminders: List<Reminder> = emptyList(),
)