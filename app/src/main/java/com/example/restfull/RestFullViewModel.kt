package com.example.restfull
import androidx.lifecycle.ViewModel
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import android.content.Context
import androidx.lifecycle.map

class RestFullViewModel : ViewModel() {
    val alarmList: MutableList<Alarm> = arrayListOf(Alarm("Default",100,null,75, true))
    val soundList = mutableListOf<Tone>()
    fun add(threshold : Int, name : String, sound : Uri, volume : Int, on : Boolean)  {
        val x : Alarm  = Alarm(name,threshold,sound,volume, on)
        alarmList.add(x)
    }

    fun percent(pos : Int, percent : Int) {
        alarmList[pos].threshold = percent
    }

    fun onoff(pos : Int) {
        alarmList[pos].on = !(alarmList[pos].on)!!
    }

    fun sound(pos: Int, sound: Uri) {
        alarmList[pos].sound = sound
    }

    fun volume(pos : Int, vol : Int) {
        alarmList[pos].volume = vol
    }



    fun genTitles() : List<String> {
        val title = soundList.map {it.title}
        return title

    }

}