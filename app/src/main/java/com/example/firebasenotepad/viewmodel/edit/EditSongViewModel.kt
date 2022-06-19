package com.example.firebasenotepad.viewmodel.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebasenotepad.model.SongsRepository
import com.example.firebasenotepad.model.entities.Song

class EditSongViewModel(val songIndex:Int):ViewModel() {
    class Factory(private val songIndex:Int):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditSongViewModel::class.java)) {
                return EditSongViewModel(songIndex) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
    enum class Mode {
        CREATE,
        EDIT
    }
    private val _mode = MutableLiveData(
        if(songIndex>=0 && songIndex<SongsRepository.songsList.size) Mode.EDIT else Mode.CREATE
    )
    val mode: LiveData<Mode> get()=_mode

    var data=MutableLiveData(
        if(songIndex>=0 && songIndex<SongsRepository.songsList.size) SongsRepository.songsList[songIndex] else Song()
    )

    private val _navigateToGallery = MutableLiveData<Boolean>()
    val navigateToGallery: LiveData<Boolean> get()=_navigateToGallery
    fun resetNavigateToGallery(){_navigateToGallery.value = false}

    fun onClickSave(){
        if(songIndex<0){
            SongsRepository.songsList.add(data.value!!)
        } else {
            SongsRepository.songsList[songIndex] = data.value!!
        }
        SongsRepository.saveSongs()
        _navigateToGallery.value=true
    }
    fun onClickDelete(){
        SongsRepository.songsList.removeAt(songIndex)
        SongsRepository.saveSongs()
        _navigateToGallery.value=true
    }
}