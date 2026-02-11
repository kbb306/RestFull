package com.example.restfull
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Todo Convert to viewBinding
class Adapter(private val alarmList: List<Alarm>) :
    RecyclerView.Adapter<Adapter.Viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.alarm_card,
            parent,false)
            return Viewholder(view)
        }

    override fun onBindViewHolder(holder: Adapter.Viewholder, position: Int) {
        val alarmItem = alarmList[position]
        holder.alarmName.text = alarm.name
        holder.alarmPercent.text = alarm.threshold.toString()

    }

    override fun getItemCount(): Int {
        return alarmList.size
    }
    class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val alarmName : TextView =
            itemView.findViewById(R.id.alarmname)
        val alarmPercent : TextView =
            itemView.findViewById(R.id.custompercent)
    }
    }



