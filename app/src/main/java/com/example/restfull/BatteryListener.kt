package com.example.restfullsimple
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import androidx.annotation.RequiresApi
import kotlin.let

class BatteryListener(context : Context) {
    val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
        context.registerReceiver(null, ifilter)
    }
    val level: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
    val scale: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
    val status = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS,-1) ?: -1
    val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
    @RequiresApi(Build.VERSION_CODES.P)
    var per : Int? = 0
        @RequiresApi(Build.VERSION_CODES.P)
        set(value) {
            field = when {
                level == -1 || scale == -1 -> null
                else -> level/scale * 100
            }

        }

}
