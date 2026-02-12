package com.example.restfull
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.example.restfull.databinding.ActivityMainBinding
import androidx.activity.viewModels



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : RestFullViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val recycler = binding.scratchpad
        binding.filler.setOnSeekBarChangeListener(object  : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                viewModel
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
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