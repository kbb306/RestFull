package com.example.restfull

class Alarm {
    var name : String? = null
    var threshold : Int? = null
    var sound : String? = null

    constructor(name:String, threshold:Int, sound:String) {
        require(name != "" && threshold in (0..100))
        this.name = name
        this.threshold = threshold
        this.sound = sound
    }

    fun check() : String? { // Consider moving to Viewmodel?
        var batt : Int = TODO("API Call here")
        return when {
            this.threshold!! >= batt -> this.sound
            else -> "not yet"
        }
    }
}