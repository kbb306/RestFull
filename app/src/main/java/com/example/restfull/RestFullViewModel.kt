package com.example.restfull
import android.app.Application
import android.app.PendingIntent
import androidx.lifecycle.ViewModel
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.map
import com.example.restfullsimple.BatteryListener

class RestFullViewModel(application: Application) : AndroidViewModel(application) {
    val alarmList: MutableList<Alarm> = arrayListOf(Alarm("Default",100,100,null, true))
    val batt = BatteryListener(application.applicationContext)
    val hax = AlarmSpawner(application.applicationContext)
    val soundObject = Sounds(application.applicationContext)
    val soundList = soundObject.getSounds()
    fun add(threshold : Int, name : String, sound : Uri, volume : Int, on : Boolean)  {
        val x : Alarm  = Alarm(name,threshold,volume,sound,on)
        alarmList.add(x)
    }

    fun percent(pos : Int, percent : Int) {
        alarmList[pos].threshold = percent
    }

    fun onoff(pos : Int) {
        alarmList[pos].on = !(alarmList[pos].on)
    }

    fun sound(pos: Int, sound: Uri) {
        alarmList[pos].sound = sound
    }

    fun volume(pos : Int, vol : Int) {
        alarmList[pos].volume = vol
    }

    fun display (pos: Int) : String {
        val string : String = alarmList[pos].threshold.toString()
        return string
    }

    fun setRepeat() {
        val toRedo = Intent()
    }






    fun genTitles() : List<String> {
        val title = soundList.map {it.title}
        return title

    }

}