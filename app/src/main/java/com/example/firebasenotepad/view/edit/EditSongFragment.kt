package com.example.firebasenotepad.view.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firebasenotepad.databinding.EditsongFragmentBinding
import com.example.firebasenotepad.viewmodel.edit.EditSongViewModel

class EditSongFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = EditsongFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val songIndex = EditSongFragmentArgs.fromBundle(
            requireArguments()
        ).songIndex

        val factory = EditSongViewModel.Factory(songIndex)
        val viewModel = ViewModelProvider(this,factory).get(EditSongViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.navigateToGallery.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(EditSongFragmentDirections.returnEditSongToGallery())
                viewModel.resetNavigateToGallery()
            }
        }

        return binding.root
    }
}