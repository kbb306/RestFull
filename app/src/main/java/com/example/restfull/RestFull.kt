package com.example.restfull

class Watcher {
    var default = Alarm(100,"default")
    var Alarms = mutableListOf(default)
}

class Alarm(var threshold : Int,var name : String) {
    init {
        require(threshold in 1..<100)
        this.threshold = threshold
        this.name = name
    }


}