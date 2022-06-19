package com.example.firebasenotepad.viewmodel.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebasenotepad.model.GamesRepository
import com.example.firebasenotepad.model.entities.Game

class EditGameViewModel(val gameIndex:Int):ViewModel() {
    class Factory(private val gameIndex:Int):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditGameViewModel::class.java)) {
                return EditGameViewModel(gameIndex) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
    enum class Mode {
        CREATE,
        EDIT
    }
    private val _mode = MutableLiveData(
        if(gameIndex>=0 && gameIndex<GamesRepository.gamesList.size) Mode.EDIT else Mode.CREATE
    )
    val mode: LiveData<Mode> get()=_mode

    var data=MutableLiveData(
        if(gameIndex>=0 && gameIndex<GamesRepository.gamesList.size) GamesRepository.gamesList[gameIndex] else Game()
    )

    private val _navigateToGallery = MutableLiveData<Boolean>()
            val navigateToGallery: LiveData<Boolean> get()=_navigateToGallery
            fun resetNavigateToGallery(){_navigateToGallery.value = false}

    fun onClickSave(){
        if(gameIndex<0){
            GamesRepository.gamesList.add(data.value!!)
        } else {
            GamesRepository.gamesList[gameIndex] = data.value!!
        }
        GamesRepository.saveGames()
        _navigateToGallery.value=true
    }
    fun onClickDelete(){
        GamesRepository.gamesList.removeAt(gameIndex)
        GamesRepository.saveGames()
        _navigateToGallery.value=true
    }
}