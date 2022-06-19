package com.example.firebasenotepad.view.gallery.tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebasenotepad.databinding.SongGalleryListBinding
import com.example.firebasenotepad.model.SongsRepository
import com.example.firebasenotepad.view.SongGalleryAdapter
import com.example.firebasenotepad.viewmodel.CheckboxListener
import com.example.firebasenotepad.viewmodel.ClickListener
import com.example.firebasenotepad.viewmodel.gallery.GalleryController

class SongsTab(): GalleryTab() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("dbg","onCreate(SongsTab)")
        val binding = SongGalleryListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = SongGalleryAdapter(
            ClickListener    { idx -> controller.onClickItem(idx) },
            CheckboxListener { idx,st -> controller.onCheckItem(idx,st) }
        )
        binding.rvSongs.adapter = adapter

        SongsRepository.songsListRef.observe(viewLifecycleOwner){
            adapter.submitList(it)
            //Log.d("dbg","${adapter.currentList} size is 0 ${adapter.currentList.size==0}")
            binding.ivSongIcon.visibility = if(adapter.currentList.size==0) View.VISIBLE else View.INVISIBLE
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d("dbg","onStart(SongsTab)")
        SongsRepository.loadSongs()
    }
}