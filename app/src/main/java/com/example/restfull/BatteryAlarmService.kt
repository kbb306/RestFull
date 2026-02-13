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
        if (!battman.isCharging) {
            Toast.makeText(
                applicationContext, "Cannot be used while battery is not charging",
                Toast.LENGTH_LONG
            ).show()
            stopSelf()
        }
        val alarmlist = alarmList[0]

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