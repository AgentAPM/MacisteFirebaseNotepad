package com.example.firebasenotepad.viewmodel.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebasenotepad.model.MoviesRepository
import com.example.firebasenotepad.model.entities.Movie

class EditMovieViewModel(val movieIndex:Int):ViewModel() {
    class Factory(private val movieIndex:Int):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditMovieViewModel::class.java)) {
                return EditMovieViewModel(movieIndex) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
    enum class Mode {
        CREATE,
        EDIT
    }
    private val _mode = MutableLiveData(
        if(movieIndex>=0 && movieIndex<MoviesRepository.moviesList.size) Mode.EDIT else Mode.CREATE
    )
    val mode: LiveData<Mode> get()=_mode

    var data=MutableLiveData(
        if(movieIndex>=0 && movieIndex<MoviesRepository.moviesList.size) MoviesRepository.moviesList[movieIndex] else Movie()
    )

    private val _navigateToGallery = MutableLiveData<Boolean>()
    val navigateToGallery: LiveData<Boolean> get()=_navigateToGallery
    fun resetNavigateToGallery(){_navigateToGallery.value = false}

    fun onClickSave(){
        if(movieIndex<0){
            MoviesRepository.moviesList.add(data.value!!)
        } else {
            MoviesRepository.moviesList[movieIndex] = data.value!!
        }
        MoviesRepository.saveMovies()
        _navigateToGallery.value=true
    }
    fun onClickDelete(){
        MoviesRepository.moviesList.removeAt(movieIndex)
        MoviesRepository.saveMovies()
        _navigateToGallery.value=true
    }
}