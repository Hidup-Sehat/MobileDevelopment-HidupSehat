package com.bangkit23.hidupsehat.presentation.screen.reminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.reminder.Reminder
import com.bangkit23.hidupsehat.domain.usecase.reminder.ReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderUseCase: ReminderUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ReminderState())
    val state = _state.asStateFlow()

    init {
        getReminders()
    }

    fun onEvent(event: ReminderEvent) {
        when (event) {
            is ReminderEvent.OnSwitchCheckedChanged -> {
                updateStatusReminder(event.isActive, event.reminder)
            }
            is ReminderEvent.OnItemClick -> {
                _state.update {
                    it.copy(
                        editedReminder = event.reminder
                    )
                }
            }
            is ReminderEvent.UpdateReminder -> {
                updateTimeReminder(event.reminder)
            }
        }
    }

    private fun updateStatusReminder(isActive: Boolean, reminder: Reminder) = viewModelScope.launch {
        val updatedReminder = reminder.copy(isActive = isActive)
        reminderUseCase.updateReminder(updatedReminder)
        getReminders()
    }

    private fun updateTimeReminder(reminder: Reminder) = viewModelScope.launch {
        reminderUseCase.updateReminder(reminder)
        getReminders()
    }

    private fun getReminders() = viewModelScope.launch {
        reminderUseCase.getEatingReminders().combine(reminderUseCase.getActivityReminders()) { eatReminders, activityReminders ->
            eatReminders to activityReminders
        }.collect { pair ->
            _state.update {
                it.copy(
                    eatReminders = pair.first,
                    activityReminders = pair.second
                )
            }
        }
    }
}