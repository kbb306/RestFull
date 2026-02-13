package com.example.restfull

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Alarm(var name : String, var threshold : Int, var volume : Int, var sound : Uri?, var on : Boolean) : Parcelable {

}