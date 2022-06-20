package com.example.firebasenotepad.viewmodel.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class GalleryController {
    protected val _navigateToEdit = MutableLiveData(-1)
              val navigateToEdit:LiveData<Int> get()=_navigateToEdit
              fun resetNavigateToEdit(){ _navigateToEdit.value = -1 }

    open fun onAdd() { Log.d("dbg","OnAdd"); _navigateToEdit.value = -2; }
    open fun onClickItem(index:Int) { Log.d("dbg","OnClick $index"); _navigateToEdit.value = index; }
    abstract fun onCheckItem(index:Int,state:Boolean)
    abstract fun loadItems()
    abstract fun saveItems()
}