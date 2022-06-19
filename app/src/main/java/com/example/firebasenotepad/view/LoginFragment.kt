package com.example.firebasenotepad.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firebasenotepad.R
import com.example.firebasenotepad.databinding.LoginFragmentBinding
import com.example.firebasenotepad.viewmodel.LoginViewModel

class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = LoginFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.viewModel = viewModel

        viewModel.ld_toastToShow.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                viewModel.resetToastToShow()
            }
        }
        viewModel.navigateToGallery.observe(viewLifecycleOwner) {
            if(it){
                findNavController().navigate(R.id.action_login_to_Gallery)
                viewModel.resetNavigateToGallery()
            }
        }
        return binding.root
    }
}