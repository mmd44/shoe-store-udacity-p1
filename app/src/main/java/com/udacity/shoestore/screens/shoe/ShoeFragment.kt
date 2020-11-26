package com.udacity.shoestore.screens.shoe

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeFragmentBinding
import timber.log.Timber


class ShoeFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: ShoeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_fragment,
            container,
            false
        )

        setHasOptionsMenu(true)

        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            it.forEach { shoe ->
                val view: View = LayoutInflater.from(activity).inflate(
                    R.layout.shoe_item_layout,
                    null
                )
                view.findViewById<TextView>(R.id.name_text).text = shoe.name
                view.findViewById<TextView>(R.id.company_text).text = shoe.company
                view.findViewById<TextView>(R.id.size_text).text = shoe.size.toString()
                view.findViewById<TextView>(R.id.desc_text).text = shoe.description
                binding.shoeListLayout.addView(view)
                Timber.d("list size: ${it.size}")
            }
        })

        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this

        binding.addShoeButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(ShoeFragmentDirections.actionShoeFragmentToShoeDetailFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logoutMenuButton -> onLogout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onLogout() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Are you sure you want to logout?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                view?.findNavController()
                    ?.navigate(ShoeFragmentDirections.actionShoeDestToMainActivity())
                requireActivity().finish()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

}

