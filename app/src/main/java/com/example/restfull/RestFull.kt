package com.example.restfull

class Watcher {
    var default = Alarm(100,"default")
    var Alarms = mutableListOf(default)

    fun getAlarm(i : Int): Alarm {
        return Alarms[i]
    }
}

class Alarm(var initthreshold : Int,var initname : String) {

    init {
        require(initthreshold in 1..<100)
    }
    var threshold = initthreshold
    var name = initname




}