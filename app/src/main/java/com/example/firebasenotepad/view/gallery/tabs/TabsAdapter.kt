package com.example.firebasenotepad.view.gallery.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasenotepad.databinding.GalleryTabBinding

class TabsAdapter():RecyclerView.Adapter<TabsAdapter.ViewHolder>() {
    class ViewHolder(val binding: GalleryTabBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GalleryTabBinding.inflate(layoutInflater)
                return ViewHolder(binding)
            }
        }
        fun bind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}