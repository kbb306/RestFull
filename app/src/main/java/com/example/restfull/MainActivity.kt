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



        filler.setOnSeekBarChangeListener(object  : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                val percent = when{
                    progress <= 0 -> 0
                    progress >= filler.max -> 100/1
                    else -> 100/(filler.max -progress)
                        }
                viewModel.percent(0,percent)
                percentbox.text = Editable.Factory.getInstance().newEditable(viewModel.display(0))
                val color = when {
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
                val input = s.toString().toInt()
                val num = when {
                    input <= 100 -> input
                    else -> 100
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