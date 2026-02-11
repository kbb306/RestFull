package com.example.restfull
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.core.view.WindowInsetsCompat
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
        binding.filler.setOnSeekBarChangeListener { view : View ->

        }

    }
}