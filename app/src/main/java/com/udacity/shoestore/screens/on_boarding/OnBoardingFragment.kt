package com.udacity.shoestore.screens.on_boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.OnBoardingFragmentBinding

class OnBoardingFragment : Fragment () {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : OnBoardingFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.on_boarding_fragment,
            container,
            false
        )

        binding.goToShoeButton.setOnClickListener { view ->
            view.findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingDestToShoeFragment())
        }

        return binding.root
    }
}