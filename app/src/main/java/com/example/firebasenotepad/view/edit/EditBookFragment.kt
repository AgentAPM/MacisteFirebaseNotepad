package com.example.firebasenotepad.view.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firebasenotepad.databinding.EditbookFragmentBinding
import com.example.firebasenotepad.viewmodel.edit.EditBookViewModel
import com.example.firebasenotepad.viewmodel.edit.EditGameViewModel

class EditBookFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = EditbookFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val gameIndex = EditGameFragmentArgs.fromBundle(requireArguments()).gameIndex

        val factory = EditGameViewModel.Factory(gameIndex)
        val viewModel = ViewModelProvider(this,factory).get(EditBookViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.navigateToGallery.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(EditGameFragmentDirections.returnEditGameToGallery())
                viewModel.resetNavigateToGallery()
            }
        }

        return binding.root
    }
}