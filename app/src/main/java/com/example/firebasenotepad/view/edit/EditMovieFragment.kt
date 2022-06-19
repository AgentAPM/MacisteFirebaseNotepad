package com.example.firebasenotepad.view.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firebasenotepad.databinding.EditmovieFragmentBinding
import com.example.firebasenotepad.viewmodel.edit.EditMovieViewModel

class EditMovieFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = EditmovieFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val movieIndex = EditMovieFragmentArgs.fromBundle(
            requireArguments()
        ).movieIndex

        val factory = EditMovieViewModel.Factory(movieIndex)
        val viewModel = ViewModelProvider(this,factory).get(EditMovieViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.navigateToGallery.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(EditMovieFragmentDirections.returnEditMovieToGallery())
                viewModel.resetNavigateToGallery()
            }
        }

        return binding.root
    }
}