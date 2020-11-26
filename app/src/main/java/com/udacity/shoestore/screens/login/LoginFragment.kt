package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment () {

   private lateinit var binding : LoginFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        binding.loginButton.setOnClickListener { view ->
            if (validateInput()) view.findNavController().navigate(LoginFragmentDirections.actionLoginDestToWelcomeFragment())
        }

        binding.registerButton.setOnClickListener { view ->
            if (validateInput()) view.findNavController().navigate(LoginFragmentDirections.actionLoginDestToWelcomeFragment())
        }
        return binding.root
    }

    private fun validateInput () : Boolean {
        val email: String = binding.emailEdit.text.toString()
        val password: String = binding.passwordEdit.text.toString()

        if(email.trim().isNotEmpty() && password.trim().isNotEmpty()) {
            return true
        }else{
            Toast.makeText(requireContext(), "Please enter your email and password!", Toast.LENGTH_SHORT).show()
        }
        return false
    }
}