package com.vaibhav.chatofy.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vaibhav.chatofy.R
import com.vaibhav.chatofy.databinding.FragmentRegisterBinding
import com.vaibhav.chatofy.util.viewBinding


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding by viewBinding(FragmentRegisterBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goToLoginScreen.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}