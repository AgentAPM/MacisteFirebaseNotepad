package com.example.firebasenotepad.viewmodel.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GalleryViewModel(private val _controllers:LiveData<List<GalleryController>>): ViewModel() {
    class Factory(val controllers:List<GalleryController>):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
                return GalleryViewModel(MutableLiveData(controllers)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


    private val _currentTabIndex = MutableLiveData(0)
            val currentTabIndex:LiveData<Int> get() = _currentTabIndex

    fun onClickAdd(){
        _controllers.value!![currentTabIndex.value!!].onAdd()
    }

    fun onTabChanged(pageID:Int){
        Log.d("dbg","$pageID")
        _currentTabIndex.value = pageID
        _controllers.value!![pageID].loadItems()
    }

}