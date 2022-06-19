package com.example.firebasenotepad.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebasenotepad.model.entities.Song
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


object SongsRepository {
    private val databaseURL = "https://mobintmaciste-default-rtdb.europe-west1.firebasedatabase.app/"
    private val SongsURL get()="users/${AuthenticationModel.userUUID}}/songs"

    private val _songsList = MutableLiveData<MutableList<Song>>()
    val songsListRef:LiveData<MutableList<Song>> get()= _songsList
    val songsList:MutableList<Song> get()= _songsList.value!!

    private var useCache = false
    fun invalidateCache(){useCache=false}
    fun loadSongs(){
        if(useCache){
            //Set value anyways to trigger observers
            _songsList.value= _songsList.value
            return
        }
        if(AuthenticationModel.userUUID==null) return

        val task = FirebaseDatabase.getInstance(databaseURL).getReference("$SongsURL/value").get()
        task.addOnSuccessListener { it ->
            //Log.d("dbg","$it")
            val list: MutableList<Song> = mutableListOf()
            it.children.forEach{
                list.add(it.getValue(Song::class.java)!!)
            }
            //Log.d("dbg","$list")
            _songsList.value = list
        }
        task.addOnFailureListener { it->
            Log.d("dbg","Firebase fetch failed. Message = ${it.message}")
        }
        useCache = true
    }
    fun saveSongs(){
        if(AuthenticationModel.userUUID==null) return

        val task = FirebaseDatabase.getInstance(databaseURL).getReference(SongsURL).setValue(songsListRef)

        useCache = false
    }
    fun getDistinctAuthors():List<String>{
        val list= mutableListOf<String>()
        for (song in songsList) {
            list.add(song.author)
        }
        return list.distinct()
    }
    fun getDistinctShops():List<String>{
        val list= mutableListOf<String>()
        for (song in songsList) {
            list.add(song.store)
        }
        return list.distinct()
    }
}