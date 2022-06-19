package com.example.firebasenotepad.view.gallery.tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebasenotepad.databinding.BookGalleryListBinding
import com.example.firebasenotepad.model.BooksRepository
import com.example.firebasenotepad.view.BookGalleryAdapter
import com.example.firebasenotepad.viewmodel.CheckboxListener
import com.example.firebasenotepad.viewmodel.ClickListener
import com.example.firebasenotepad.viewmodel.gallery.GalleryController

class BooksTab(): GalleryTab() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("dbg","onCreate(BooksTab)")
        val binding = BookGalleryListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = BookGalleryAdapter(
            ClickListener    { idx -> controller.onClickItem(idx) },
            CheckboxListener { idx,st -> controller.onCheckItem(idx,st) }
        )
        binding.rvBooks.adapter = adapter

        BooksRepository.booksListRef.observe(viewLifecycleOwner){
            adapter.submitList(it)
            //Log.d("dbg","${adapter.currentList} size is 0 ${adapter.currentList.size==0}")
            binding.ivBookIcon.visibility = if(adapter.currentList.size==0) View.VISIBLE else View.INVISIBLE
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d("dbg","onStart(BooksTab)")
        BooksRepository.loadBooks()
    }
}