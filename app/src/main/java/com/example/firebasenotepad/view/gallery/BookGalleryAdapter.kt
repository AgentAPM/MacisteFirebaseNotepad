package com.example.firebasenotepad.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasenotepad.databinding.BookListitemBinding
import com.example.firebasenotepad.model.entities.Book
import com.example.firebasenotepad.viewmodel.CheckboxListener
import com.example.firebasenotepad.viewmodel.ClickListener

class BookGalleryAdapter(
    private val clickListener: ClickListener,
    private val checkboxListener: CheckboxListener
): ListAdapter<Book, BookGalleryAdapter.ViewHolder>(BookDiffCallback()) {
    class ViewHolder(private val binding:BookListitemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object{
            fun from(parent:ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BookListitemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
        fun bind(book:Book, clickListener: ClickListener, checkboxListener: CheckboxListener){
            binding.data = book
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

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.name == newItem.name && oldItem.author == newItem.author
    }
    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}