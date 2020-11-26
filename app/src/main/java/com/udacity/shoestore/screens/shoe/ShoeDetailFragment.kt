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
import com.udacity.shoestore.databinding.ShoeFragmentBinding
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

        binding.confirmButton.setOnClickListener {
            if (validateInput()) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Are you sure you want to add this shoe?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        val s = Shoe(
                            binding.nameEdit.text.toString(),
                            binding.sizeEdit.text.toString().toDouble(),
                            binding.companyEdit.text.toString(),
                            binding.descEdit.text.toString()
                        )
                        viewModel.addNewShoe(s)
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
            it.findNavController().popBackStack()
        }

        return binding.root
    }

    private fun validateInput(): Boolean {
        val name: String = binding.nameEdit.text.toString()
        val size: String = binding.sizeEdit.text.toString()
        val company: String = binding.companyEdit.text.toString()
        val desc: String = binding.descEdit.text.toString()

        if (name.trim().isNotEmpty() && size.trim().isNotEmpty() && company.trim()
                .isNotEmpty() && desc.trim().isNotEmpty()
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