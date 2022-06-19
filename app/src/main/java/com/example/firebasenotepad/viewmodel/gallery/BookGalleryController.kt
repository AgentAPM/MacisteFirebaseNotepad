package com.example.firebasenotepad.viewmodel.gallery

import com.example.firebasenotepad.model.BooksRepository

class BookGalleryController: GalleryController() {
    override fun onCheckItem(index: Int, state:Boolean) {
        val selectedBook = BooksRepository.booksList[index]
        //Log.d("dbg","checked $index - ${selectedBook.name} to ${selectedBook.completed}")
        selectedBook.apply { completed = state; }
        //Log.d("dbg","completed is ${GamesRepository.gamesList[gameIndex].completed}")
        BooksRepository.saveBooks()
    }

    override fun loadItems() {
        BooksRepository.loadBooks()
    }
    override fun saveItems() {
        BooksRepository.saveBooks()
    }
}