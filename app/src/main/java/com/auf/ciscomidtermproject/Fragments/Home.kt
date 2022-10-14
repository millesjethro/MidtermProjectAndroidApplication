package com.auf.ciscomidtermproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.auf.ciscomidtermproject.R
import com.auf.ciscomidtermproject.databinding.FragmentBookingBinding
import com.auf.ciscomidtermproject.databinding.FragmentHomeBinding
import com.auf.ciscomidtermproject.databinding.FragmentListBinding


class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }
}