package com.example.firebasenotepad.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebasenotepad.model.entities.Game
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


object GamesRepository {
    private val databaseURL = "https://mobintmaciste-default-rtdb.europe-west1.firebasedatabase.app/"
    private val GamesURL get()="users/${AuthenticationModel.userUUID}}/games"

    private val _gamesList = MutableLiveData<MutableList<Game>>()
            val gamesListRef:LiveData<MutableList<Game>> get()= _gamesList
            val gamesList:MutableList<Game> get()= _gamesList.value!!

    private var useCache=false
            fun invalidateCache(){useCache=false}

    fun loadGames(){
        if(useCache){
            //Set value anyways to trigger observers
            _gamesList.value= _gamesList.value
            return
        }
        if(AuthenticationModel.userUUID==null) return

        val task = FirebaseDatabase.getInstance(databaseURL).getReference("$GamesURL/value").get()
        task.addOnSuccessListener { it ->
            //Log.d("dbg","$it")
            val list: MutableList<Game> = mutableListOf()
            it.children.forEach{
                list.add(it.getValue(Game::class.java)!!)
            }
            //Log.d("dbg","$list")
            _gamesList.value = list
        }
        task.addOnFailureListener { it->
            Log.d("dbg","Firebase fetch failed. Message = ${it.message}")
        }

        useCache = true
    }
    fun saveGames(){
        if(AuthenticationModel.userUUID==null) return

        val task = FirebaseDatabase.getInstance(databaseURL).getReference(GamesURL).setValue(gamesListRef)

        useCache = false
    }
    fun getDistinctAuthors():List<String>{
        val list= mutableListOf<String>()
        for (game in gamesList) {
            list.add(game.author)
        }
        return list.distinct()
    }

    fun getDistinctShops():List<String>{
        val list= mutableListOf<String>()
        for (game in gamesList) {
            list.add(game.store)
        }
        return list.distinct()
    }
}