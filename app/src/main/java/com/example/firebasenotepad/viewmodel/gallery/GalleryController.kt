package com.example.firebasenotepad.viewmodel.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class GalleryController {
    protected val _navigateToEdit = MutableLiveData(-1)
              val navigateToEdit:LiveData<Int> get()=_navigateToEdit
              fun resetNavigateToEdit(){ _navigateToEdit.value = -1 }

    open fun onAdd() {_navigateToEdit.value = -2; Log.d("dbg","OnAdd")}
    open fun onClickItem(index:Int) {_navigateToEdit.value = index; Log.d("dbg","OnClick $index")}
    abstract fun onCheckItem(index:Int,state:Boolean)
    abstract fun loadItems()
    abstract fun saveItems()
}