package com.example.firebasenotepad.view.gallery.tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebasenotepad.databinding.GameGalleryListBinding
import com.example.firebasenotepad.model.GamesRepository
import com.example.firebasenotepad.view.GameGalleryAdapter
import com.example.firebasenotepad.viewmodel.CheckboxListener
import com.example.firebasenotepad.viewmodel.ClickListener
import com.example.firebasenotepad.viewmodel.gallery.GalleryController

class GamesTab(): GalleryTab() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("dbg","onCreate(GamesTab)")
        val binding = GameGalleryListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = GameGalleryAdapter(
            ClickListener    { idx -> controller.onClickItem(idx) },
            CheckboxListener { idx,st -> controller.onCheckItem(idx,st) }
        )
        binding.rvGames.adapter = adapter

        GamesRepository.gamesListRef.observe(viewLifecycleOwner){
            adapter.submitList(it)
            //Log.d("dbg","${adapter.currentList} size is 0 ${adapter.currentList.size==0}")
            binding.ivGameIcon.visibility = if(adapter.currentList.size==0) View.VISIBLE else View.INVISIBLE
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d("dbg","onStart(GamesTab)")
        GamesRepository.loadGames()
    }
}