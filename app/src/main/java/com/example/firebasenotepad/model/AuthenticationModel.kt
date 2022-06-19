package com.example.firebasenotepad.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object AuthenticationModel {
    fun LogIn(login:String, password:String): Task<AuthResult> {
        val task = Firebase.auth.signInWithEmailAndPassword(login,password)
        return task
    }
    fun LogOut(){
        Firebase.auth.signOut()
    }
    val userUUID:String? get()=Firebase.auth.uid
}