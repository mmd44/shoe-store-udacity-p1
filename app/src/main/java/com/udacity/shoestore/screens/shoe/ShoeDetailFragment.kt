package com.udacity.shoestore.screens.shoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: ShoeDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_detail_fragment,
            container,
            false
        )

        binding.shoeViewModel = viewModel

        binding.confirmButton.setOnClickListener {
            if (validateInput()) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Are you sure you want to add this shoe?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        viewModel.addNewShoe()
                        viewModel.resetShoeAddition()
                        it.findNavController().popBackStack()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }
        }

        binding.cancelButton.setOnClickListener {
            viewModel.resetShoeAddition()
            it.findNavController().popBackStack()
        }

        return binding.root
    }

    private fun validateInput(): Boolean {

        if (viewModel.newShoe.name.trim().isNotEmpty() && viewModel.newShoe.size.toString().trim()
                .isNotEmpty() && viewModel.newShoe.company.trim()
                .isNotEmpty() && viewModel.newShoe.description.trim().isNotEmpty()
        ) {
            return true
        } else {
            Toast.makeText(
                requireContext(),
                "Please enter all the fields!",
                Toast.LENGTH_SHORT
            ).show()
        }
        return false
    }
}