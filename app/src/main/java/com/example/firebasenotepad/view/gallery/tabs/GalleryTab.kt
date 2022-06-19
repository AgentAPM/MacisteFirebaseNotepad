package com.example.firebasenotepad.view.gallery.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebasenotepad.databinding.GalleryTabBinding
import com.example.firebasenotepad.viewmodel.gallery.GalleryController

open class GalleryTab: Fragment() {
    lateinit var controller:GalleryController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = GalleryTabBinding.inflate(inflater)
        binding.lifecycleOwner = this


        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    companion object{
        fun newInstance():Fragment{
            return GalleryTab()
        }
    }
}