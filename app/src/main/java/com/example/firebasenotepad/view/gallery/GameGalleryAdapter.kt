package com.example.firebasenotepad.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasenotepad.databinding.GameListitemBinding
import com.example.firebasenotepad.model.entities.Game
import com.example.firebasenotepad.viewmodel.CheckboxListener
import com.example.firebasenotepad.viewmodel.ClickListener

class GameGalleryAdapter(
    private val clickListener: ClickListener,
    private val checkboxListener: CheckboxListener
): ListAdapter<Game, GameGalleryAdapter.ViewHolder>(GameDiffCallback()) {
    class ViewHolder(private val binding:GameListitemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object{
            fun from(parent:ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GameListitemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
        fun bind(game:Game, clickListener: ClickListener, checkboxListener: CheckboxListener){
            binding.data = game
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

class GameDiffCallback : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.name == newItem.name && oldItem.author == newItem.author
    }
    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }
}