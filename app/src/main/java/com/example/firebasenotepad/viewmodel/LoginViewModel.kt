package com.example.firebasenotepad.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasenotepad.model.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel:ViewModel() {
    val loginInput = MutableLiveData<String>("student@polsl.pl")
    val passwordInput = MutableLiveData<String>("Zaq12wsx")
    val passwordRepeatInput = MutableLiveData<String>()

    private val _ld_toastToShow = MutableLiveData<String>()
            val ld_toastToShow:LiveData<String> get() = _ld_toastToShow
            fun resetToastToShow() { _ld_toastToShow.value="" }

    private val _navigateToGallery=MutableLiveData<Boolean>()
            val navigateToGallery:LiveData<Boolean> get()=_navigateToGallery
            fun resetNavigateToGallery(){_navigateToGallery.value=false}

    private val _showRepeatPassword = MutableLiveData(false)
            val showRepeatPassword: LiveData<Boolean> get()=_showRepeatPassword

    fun onClickLogin(){
        _showRepeatPassword.value=false

        val task = AuthenticationModel.LogIn(loginInput.value!!,passwordInput.value!!)
        task.addOnSuccessListener {
            Log.d("dbg","Successfully logged in")
            _navigateToGallery.value=true
        }
        task.addOnFailureListener {
            _ld_toastToShow.value = "Couldn't sign in"
            Log.d("dbg",it.message!!)
        }
    }
    fun onClickRegister(){
        if (passwordRepeatInput.value == passwordInput.value ) {
            Log.d("dbg", "register: ${loginInput.value}  password: ${passwordInput.value}")
        }
        _showRepeatPassword.value=true

    }
    fun onLogOut(){
        AuthenticationModel.LogOut()
        
        BooksRepository.invalidateCache()
        GamesRepository.invalidateCache()
        MoviesRepository.invalidateCache()
        SongsRepository.invalidateCache()
    }
}