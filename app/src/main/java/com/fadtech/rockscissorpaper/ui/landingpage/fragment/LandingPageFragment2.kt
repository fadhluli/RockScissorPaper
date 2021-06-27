package com.fadtech.rockscissorpaper.ui.landingpage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fadtech.rockscissorpaper.R
import com.fadtech.rockscissorpaper.databinding.FragmentLandingPage1Binding
import com.fadtech.rockscissorpaper.databinding.FragmentLandingPage2Binding


class LandingPageFragment2 : Fragment() {

    private lateinit var binding: FragmentLandingPage2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandingPage2Binding.inflate(inflater, container, false)
        return binding.root
    }

}