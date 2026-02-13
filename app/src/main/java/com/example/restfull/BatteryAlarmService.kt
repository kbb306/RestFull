package com.example.restfull

import android.app.Notification
import android.app.NotificationChannel
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.restfullsimple.BatteryListener

class BatteryAlarmService(context: Context): Service() {
    private val battman = BatteryListener(this)
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun buildNotification () {
        TODO("Build notification")
    }
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //val notification = buildNotification(this)
        val name=intent?.getStringExtra("name")
        Toast.makeText(
            applicationContext, "Service has started running in the background",
            Toast.LENGTH_SHORT
        ).show()
        if (name != null) {
            Log.d("Service Name",name)
        }
        Log.d("Service Status","Starting Service")
        val alarmList = mutableListOf(intent?.getParcelableArrayListExtra<Alarm>("alarmlist"))
        val alarmlist = alarmList[0]
        if (alarmlist != null) {
            while (battman.per?.plus(1) != 101) { //This is most likely catastrophically bad and unnecessary
                for (alarm in alarmlist) {
                    when {
                        alarm.on -> {
                            if (battman.per == alarm.threshold) {
                                //Call alarm (probably needs notification)
                            }

                        }

                        else -> continue
                    }

                }
            }
        }

        stopSelf()
        return START_STICKY
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d("Stopping","Stopping Service")

        return super.stopService(name)
    }

    override fun onDestroy() {
        Toast.makeText(
            applicationContext, "Service execution completed",
            Toast.LENGTH_SHORT
        ).show()
        Log.d("Stopped","Service Stopped")
        super.onDestroy()
    } //

}