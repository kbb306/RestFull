package com.example.restfull

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri

data class Tone(
    val title : String,
    val uri : Uri
)

class Sounds(context : Context) {
    val manager = RingtoneManager(context).apply { setType(RingtoneManager.TYPE_ALARM) }

    fun getSounds() : List<Tone> {
        val cursor = manager.cursor
        val list = mutableListOf<Tone>()

        while (cursor.moveToNext()) {
            val title = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            val uri = manager.getRingtoneUri(cursor.position)
            list.add(Tone(title,uri))
        }
        return list
    }

    fun getDef() : Tone {
        val title = "Default"
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        var tone = Tone(title,uri)
        return tone
    }

}