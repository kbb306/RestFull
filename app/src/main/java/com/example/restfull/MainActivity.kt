package com.example.restfull
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.example.restfull.databinding.ActivityMainBinding
import androidx.activity.viewModels
import android.text.Editable
import android.text.TextWatcher
import com.example.restfullsimple.BatteryListener
import kotlin.toString


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : RestFullViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val recycler = binding.scratchpad
        val filler = binding.filler
        val percentbox = binding.percentbox
        percentbox.setText(viewModel.getPer(0).toString())
        filler.progress = viewModel.getPer(0)/20
        //This might allow my original service code to run after all
        val alarmIntent = Intent(this, BatteryAlarmService::class.java).apply {
            putExtra("alarmlist", ArrayList(viewModel.alarmList))
        }
        startService(alarmIntent)
        filler.setOnSeekBarChangeListener(object  : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                if(fromUser){
                    val percent = progress * 20
                    viewModel.percent(0,percent)
                }

                val newText = viewModel.getPer(0).toString()
                if (percentbox.text.toString() != newText) {
                    percentbox.setText(newText)
                }
                val color = when {
                    progress == 0 -> Color.GRAY
                    progress <= 1 -> Color.RED
                    progress <= 2 -> Color.YELLOW
                    else -> Color.GREEN
                }
                val fore = ((seekBar?.progressDrawable ?: return  ) as LayerDrawable).findDrawableByLayerId(android.R.id.progress)
                fore.setTint(color)

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


        percentbox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val instring = s.toString()
                if (instring.isEmpty()) {return}
                val input = instring.toIntOrNull() ?: -1
                val num = when {
                    input <= 100 -> input
                    else -> 100
                }
                if (num != viewModel.getPer(0)) {
                    viewModel.percent(0,num)
                    filler.progress = num / 20
                }
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

        })

// Spinner population
    val titles = viewModel.genTitles()
    val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, titles)
    binding.soundspinner.adapter = adapter

// Spinner binding
        binding.soundspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val picked = viewModel.soundList[position]
                viewModel.sound(0, picked.uri)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }
}