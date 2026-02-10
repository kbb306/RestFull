package com.example.restfull
import androidx.lifecycle.ViewModel
class RestFullViewModel : ViewModel() {
    fun add(threshold : Int, name : String, sound : String) : String {
        val x : Alarm  = Alarm(name,threshold,sound)
        return x.Name
    }
}