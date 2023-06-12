package com.bangkit23.hidupsehat.infrastructure.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.bangkit23.hidupsehat.data.source.local.entity.ReminderEntity
import com.bangkit23.hidupsehat.data.source.local.room.ReminderDao
import com.bangkit23.hidupsehat.util.toDomain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BootCompleteReceiver : BroadcastReceiver() {

    @Inject
    lateinit var reminderDao: ReminderDao
    private val coroutineJob = Job()
    private val scope = CoroutineScope(Dispatchers.IO + coroutineJob)

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {
            scope.launch {
                reminderDao.getNearestActiveReminder().collect { reminderEntity ->
                    if (context != null) {
                        val reminders = reminderEntity.map(ReminderEntity::toDomain)
                        reminders.forEach {
                            ReminderAlarm.setAlarm(context, it)
                        }
                    }
                }
            }
        }
    }
}