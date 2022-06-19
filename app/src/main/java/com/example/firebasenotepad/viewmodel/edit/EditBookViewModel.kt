package com.example.firebasenotepad.viewmodel.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebasenotepad.model.BooksRepository
import com.example.firebasenotepad.model.entities.Book

class EditBookViewModel(val bookIndex:Int):ViewModel() {
    class Factory(private val bookIndex:Int):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditBookViewModel::class.java)) {
                return EditBookViewModel(bookIndex) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
    enum class Mode {
        CREATE,
        EDIT
    }
    private val _mode = MutableLiveData(
        if(bookIndex>=0 && bookIndex<BooksRepository.booksList.size) Mode.EDIT else Mode.CREATE
    )
    val mode: LiveData<Mode> get()=_mode

    var data=MutableLiveData(
        if(bookIndex>=0 && bookIndex<BooksRepository.booksList.size) BooksRepository.booksList[bookIndex] else Book()
    )

    private val _navigateToGallery = MutableLiveData<Boolean>()
    val navigateToGallery: LiveData<Boolean> get()=_navigateToGallery
    fun resetNavigateToGallery(){_navigateToGallery.value = false}

    fun onClickSave(){
        if(bookIndex<0){
            BooksRepository.booksList.add(data.value!!)
        } else {
            BooksRepository.booksList[bookIndex] = data.value!!
        }
        BooksRepository.saveBooks()
        _navigateToGallery.value=true
    }
    fun onClickDelete(){
        BooksRepository.booksList.removeAt(bookIndex)
        BooksRepository.saveBooks()
        _navigateToGallery.value=true
    }
}