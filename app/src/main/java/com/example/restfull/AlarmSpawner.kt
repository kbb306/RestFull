package com.example.restfull

import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.provider.AlarmClock

class AlarmSpawner(val context: Context) {
    fun spawn(alarm : Alarm, time : Long) {
        val cal = Calendar.getInstance()
        val timeToTarget = time/60000

        cal.add(Calendar.MINUTE, timeToTarget.toInt())
                val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                    putExtra(AlarmClock.EXTRA_HOUR, cal.get(Calendar.HOUR_OF_DAY))
                    putExtra(AlarmClock.EXTRA_MINUTES, cal.get(Calendar.MINUTE))
                    putExtra(AlarmClock.EXTRA_MESSAGE,alarm.name)
                    putExtra(AlarmClock.EXTRA_RINGTONE,alarm.sound)
                    putExtra(AlarmClock.EXTRA_SKIP_UI, true)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(intent)

        }

    fun despawn(alarm: Alarm) {
        val mute = Intent(AlarmClock.ACTION_DISMISS_ALARM).apply {
            putExtra(AlarmClock.EXTRA_ALARM_SEARCH_MODE, AlarmClock.ALARM_SEARCH_MODE_LABEL)
            putExtra(AlarmClock.EXTRA_MESSAGE,alarm.name)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(mute)
    }

}



