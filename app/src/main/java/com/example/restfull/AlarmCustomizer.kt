package com.example.restfull

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.restfull.databinding.FragmentCustalarmBinding
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged

class AlarmCustomizer : DialogFragment() {
    private var _binding: FragmentCustalarmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustalarmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.custompercent.doAfterTextChanged { text ->

        }
    }
}
