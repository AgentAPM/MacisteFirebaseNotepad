package com.example.firebasenotepad.viewmodel

import android.util.Log
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasenotepad.model.entities.Book
import com.example.firebasenotepad.model.entities.Game
import com.example.firebasenotepad.model.entities.Movie

open class GenericClickListener<T>(val clickListener: (t:T)->Unit){
    fun onClick(t:T) = clickListener(t)
}
open class GenericCheckboxListener<T>(val changeListener: (t:T)->Unit){
    fun onChange(t:T)=changeListener(t)
}

class ClickListener( val clickCallback: (index:Int) -> Unit){
    fun onClick(index: Int) = clickCallback(index)
}
class CheckboxListener( val checkboxCallback: (index:Int, state:Boolean) -> Unit){
    fun onChange(index: Int, state:Boolean) = checkboxCallback(index,state)
}

class GameClickListener(val clickListener: (game: Game) -> Unit) {
    fun onClick(game: Game) = clickListener(game)
}
class GameCheckboxListener(val changeListener: (game: Game)->Unit){
    fun onChange(game: Game) = changeListener(game)
}

class MovieClickListener(val clickListener: (movie: Movie) -> Unit) {
    fun onClick(movie:Movie) = clickListener(movie)
}
class MovieCheckboxListener(val changeListener: (movie: Movie)->Unit){
    fun onChange(movie:Movie) = changeListener(movie)
}

class BookClickListener(val clickListener: (book:Book) -> Unit) {
    fun onClick(book:Book) = clickListener(book)
}
class BookCheckboxListener(val changeListener: (book:Book)->Unit){
    fun onChange(book:Book) = changeListener(book)
}