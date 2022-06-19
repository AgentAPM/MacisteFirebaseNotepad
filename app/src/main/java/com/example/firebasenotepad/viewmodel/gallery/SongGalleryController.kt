package com.example.firebasenotepad.viewmodel.gallery

import com.example.firebasenotepad.model.SongsRepository

class SongGalleryController: GalleryController() {
    override fun onCheckItem(index: Int, state:Boolean) {
        val selectedSong = SongsRepository.songsList[index]
        //Log.d("dbg","checked $index - ${selectedSong.name} to ${selectedSong.completed}")
        selectedSong.apply { completed = state; }
        //Log.d("dbg","completed is ${GamesRepository.gamesList[gameIndex].completed}")
        SongsRepository.saveSongs()
    }

    override fun loadItems() {
        SongsRepository.loadSongs()
    }
    override fun saveItems() {
        SongsRepository.saveSongs()
    }
}