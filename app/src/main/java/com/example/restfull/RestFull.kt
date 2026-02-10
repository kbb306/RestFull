package com.example.restfull

class Alarm(Name : String, threshold: Int, sound : String) {
    init {
        require(threshold > 0 && threshold >= 100)
        require(!Name.equals(""))
    }
    var name = Name
        set(value) {
            when {
                value.equals("") -> {
                    //Raise some exception or create "untitled x"
                }
                else -> field = value
            }
        }
    var Threshold: Int = threshold
        set(value)  {
            field = when {
                value < 0 -> 0
                value > 100 -> 100
                else -> value
            }
        }

    var Sound = sound

    fun battcheck(current : Int) : Boolean {
        // API Bullshit here
        return TODO("Provide the return value")
    }
}