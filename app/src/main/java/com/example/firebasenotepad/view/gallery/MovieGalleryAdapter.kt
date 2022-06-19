package com.example.firebasenotepad.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasenotepad.databinding.MovieListitemBinding
import com.example.firebasenotepad.model.entities.Movie
import com.example.firebasenotepad.viewmodel.CheckboxListener
import com.example.firebasenotepad.viewmodel.ClickListener

class MovieGalleryAdapter(
    private val clickListener: ClickListener,
    private val checkboxListener: CheckboxListener
): ListAdapter<Movie, MovieGalleryAdapter.ViewHolder>(MovieDiffCallback()) {
    class ViewHolder(private val binding:MovieListitemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object{
            fun from(parent:ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieListitemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
        fun bind(movie:Movie, clickListener: ClickListener, checkboxListener: CheckboxListener){
            binding.data = movie
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

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.name == newItem.name && oldItem.author == newItem.author
    }
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}