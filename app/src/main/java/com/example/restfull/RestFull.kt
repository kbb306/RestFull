package com.example.restfull

import android.net.Uri

class Alarm {
    var name : String? = null
    var threshold : Int? = null
    var sound : Uri? = null
    var volume : Int? = null

    var on : Boolean? = null

    constructor(name: String, threshold: Int, sound: Uri?, volume: Int, on: Boolean) {
        require(name != "" && threshold in (0..100))
        this.name = name
        this.threshold = threshold
        this.sound = sound
        this.volume = volume
        this.on = on
    }


}