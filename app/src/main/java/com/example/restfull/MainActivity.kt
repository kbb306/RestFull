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
        val soundObject = Sounds(this)
        val soundList = soundObject.getSounds()
        viewModel.soundList.value = soundList
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
        // Code for populating and binding soundspinner. There must be a more MVVM way to do this!
        viewModel.soundList.observe(this) { tones ->
            val titles = tones.map { it.title }
            val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, titles)
            binding.soundspinner.adapter = adapter
        }

        binding.soundspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val picked = viewModel.soundList.value?.get(position)
                viewModel.sound(0, picked?.uri ?:soundObject.getDef().uri )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}