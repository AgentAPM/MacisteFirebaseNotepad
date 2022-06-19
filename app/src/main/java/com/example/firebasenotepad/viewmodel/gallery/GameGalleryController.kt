package com.example.firebasenotepad.viewmodel.gallery

import com.example.firebasenotepad.model.GamesRepository

class GameGalleryController: GalleryController() {
    override fun onCheckItem(index: Int, state:Boolean) {
        val selectedGame = GamesRepository.gamesList[index]
        //Log.d("dbg","checked $index - ${selectedGame.name} to ${selectedGame.completed}")
        selectedGame.apply { completed = state; }
        //Log.d("dbg","completed is ${GamesRepository.gamesList[gameIndex].completed}")
        GamesRepository.saveGames()
    }

    override fun loadItems() {
        GamesRepository.loadGames()
    }
    override fun saveItems() {
        GamesRepository.saveGames()
    }

}