package com.example.restfull
import androidx.lifecycle.ViewModel
class RestFullViewModel : ViewModel() {
    val alarmList: MutableList<Alarm> = arrayListOf(Alarm("Default",100,"Bells",75, true))
    fun add(threshold : Int, name : String, sound : String, volume : Int, on : Boolean)  {
        val x : Alarm  = Alarm(name,threshold,sound,volume, on)
        alarmList.add(x)
    }

    fun onoff(pos : Int,) {
        alarmList[pos].on = !(alarmList[pos].on)!!
    }


}