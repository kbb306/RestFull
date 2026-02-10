package com.example.restfull

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.restfull.databinding.FragmentCustalarmBinding

class AlarmCustomizer : DialogFragment() {
    private lateinit var binding: FragmentCustalarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCustalarmBinding.inflate(layoutInflater)
    }
}