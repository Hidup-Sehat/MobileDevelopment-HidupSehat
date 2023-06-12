package com.bangkit23.hidupsehat.infrastructure.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.bangkit23.hidupsehat.domain.model.reminder.Reminder

object ReminderAlarm {
    fun setAlarm(context: Context, reminder: Reminder) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("title", reminder.title)
            putExtra("type", reminder.type)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, reminder.id, alarmIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        if (reminder.time > System.currentTimeMillis()) {
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                reminder.time,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }

    private fun cancelAlarm(context: Context, reminder: Reminder) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, reminder.id, alarmIntent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.cancel(pendingIntent)
    }

    fun setAlarmActiveStatus(context: Context, reminder: Reminder, isActive: Boolean) {
        if (isActive) setAlarm(context, reminder)
        else cancelAlarm(context, reminder)
    }
}

