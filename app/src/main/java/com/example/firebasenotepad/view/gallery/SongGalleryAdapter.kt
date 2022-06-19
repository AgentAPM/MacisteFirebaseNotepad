package com.example.firebasenotepad.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasenotepad.databinding.SongListitemBinding
import com.example.firebasenotepad.model.entities.Song
import com.example.firebasenotepad.viewmodel.CheckboxListener
import com.example.firebasenotepad.viewmodel.ClickListener

class SongGalleryAdapter(
    private val clickListener: ClickListener,
    private val checkboxListener: CheckboxListener
): ListAdapter<Song, SongGalleryAdapter.ViewHolder>(SongDiffCallback()) {
    class ViewHolder(private val binding:SongListitemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object{
            fun from(parent:ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SongListitemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
        fun bind(song:Song, clickListener: ClickListener, checkboxListener: CheckboxListener){
            binding.data = song
            binding.clickListener = clickListener
            binding.checkboxListener = checkboxListener
            binding.listIndex = adapterPosition
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener,checkboxListener)
    }

}

class SongDiffCallback : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.name == newItem.name && oldItem.author == newItem.author
    }
    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem == newItem
    }
}