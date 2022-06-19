package com.example.firebasenotepad.view.gallery.tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebasenotepad.databinding.MovieGalleryListBinding
import com.example.firebasenotepad.model.MoviesRepository
import com.example.firebasenotepad.view.MovieGalleryAdapter
import com.example.firebasenotepad.viewmodel.CheckboxListener
import com.example.firebasenotepad.viewmodel.ClickListener
import com.example.firebasenotepad.viewmodel.gallery.GalleryController

class MoviesTab(): GalleryTab() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("dbg","onCreate(MoviesTab)")
        val binding = MovieGalleryListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = MovieGalleryAdapter(
            ClickListener    { idx -> controller.onClickItem(idx) },
            CheckboxListener { idx,st -> controller.onCheckItem(idx,st) }
        )
        binding.rvMovies.adapter = adapter

        MoviesRepository.moviesListRef.observe(viewLifecycleOwner){
            adapter.submitList(it)
            //Log.d("dbg","${adapter.currentList} size is 0 ${adapter.currentList.size==0}")
            binding.ivMovieIcon.visibility = if(adapter.currentList.size==0) View.VISIBLE else View.INVISIBLE
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d("dbg","onStart(MoviesTab)")
        MoviesRepository.loadMovies()
    }
}