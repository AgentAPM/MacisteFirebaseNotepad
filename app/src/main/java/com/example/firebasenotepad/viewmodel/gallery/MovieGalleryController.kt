package com.example.firebasenotepad.viewmodel.gallery

import android.util.Log
import com.example.firebasenotepad.model.MoviesRepository

class MovieGalleryController: GalleryController() {
    override fun onCheckItem(index: Int, state:Boolean) {
        val selectedMovie = MoviesRepository.moviesList[index]
        //Log.d("dbg","checked $index - ${selectedMovie.name} to ${selectedMovie.completed}")
        selectedMovie.apply { completed = state; }
        //Log.d("dbg","completed is ${GamesRepository.gamesList[gameIndex].completed}")
        MoviesRepository.saveMovies()
    }

    override fun loadItems() {
        MoviesRepository.loadMovies()
    }
    override fun saveItems() {
        MoviesRepository.saveMovies()
    }

}