package com.example.restfull

import android.app.NotificationChannel
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.app.NotificationManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.restfullsimple.BatteryListener

class BatteryAlarmService(context: Context): Service() {
    private lateinit var battman : BatteryListener  // Does this need application context?
    private val channelId = "restfull_service"
    private val notificationId = 3942
    private var loop = false

    private val handler = Handler(Looper.getMainLooper())

    private  var alarmList : ArrayList<Alarm>? = null
    private val tick = object : Runnable {
        override fun run() {
            if (!loop) return
            val list = alarmList
            if(list != null) {
                val per = battman.percent()
                for (alarm in list) {
                    if (alarm.on && per != null && per == alarm.threshold) {
                        Log.d("Alarm ${alarm.name}","sounding!")

                    }
                }
            }
            handler.postDelayed(this,1000)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
        battman = BatteryListener(applicationContext)
    }

   private fun buildNotification (text: String) =
       NotificationCompat.Builder(this,channelId)
           .setContentTitle("RestFull")
           .setContentText(text)
           .setOngoing(true)
           .build()


    private fun createChannel() {
        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            channelId,
            "RestFull",
            NotificationManager.IMPORTANCE_HIGH
        )
        nm.createNotificationChannel(channel)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(notificationId,buildNotification("Now listening"))
        alarmList = intent?.getParcelableArrayListExtra("alarmlist")
        if(!battman.isCharging()) {
            Toast.makeText(applicationContext,"Cannot be used while battery is not charging.",
                Toast.LENGTH_LONG).show()
            stopSelf()
            return START_NOT_STICKY
        }
        loop = true
        handler.post(tick)
        return START_STICKY
    }

    private fun update(alarm : String) {
        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(notificationId,buildNotification("Alarm $alarm sounding!"))
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d("Stopping","Stopping Service")
        loop = false
        return super.stopService(name)
    }

    override fun onDestroy() {
        Toast.makeText(
            applicationContext, "Service execution completed",
            Toast.LENGTH_SHORT
        ).show()
        loop = false
        handler.removeCallbacksAndMessages(null)
        Log.d("Stopped","Service Stopped")
        super.onDestroy()
    } //

}