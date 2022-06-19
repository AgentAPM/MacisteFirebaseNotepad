package com.example.firebasenotepad.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebasenotepad.model.entities.Movie
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


object MoviesRepository {
    private val databaseURL = "https://mobintmaciste-default-rtdb.europe-west1.firebasedatabase.app/"
    private val MoviesURL get()="users/${AuthenticationModel.userUUID}}/movies"

    private val _moviesList = MutableLiveData<MutableList<Movie>>()
            val moviesListRef:LiveData<MutableList<Movie>> get()= _moviesList
            val moviesList:MutableList<Movie> get()= _moviesList.value!!

    private var useCache = false
            fun invalidateCache(){useCache=false}
    fun loadMovies(){
        if(useCache){
            //Set value anyways to trigger observers
            _moviesList.value= _moviesList.value
            return
        }
        if(AuthenticationModel.userUUID==null) return

        val task = FirebaseDatabase.getInstance(databaseURL).getReference("$MoviesURL/value").get()
        task.addOnSuccessListener { it ->
            //Log.d("dbg","$it")
            val list: MutableList<Movie> = mutableListOf()
            it.children.forEach{
                list.add(it.getValue(Movie::class.java)!!)
            }
            //Log.d("dbg","$list")
            _moviesList.value = list
        }
        task.addOnFailureListener { it->
            Log.d("dbg","Firebase fetch failed. Message = ${it.message}")
        }
        useCache = true
    }
    fun saveMovies(){
        if(AuthenticationModel.userUUID==null) return

        val task = FirebaseDatabase.getInstance(databaseURL).getReference(MoviesURL).setValue(moviesListRef)

        useCache = false
    }
    fun getDistinctAuthors():List<String>{
        val list= mutableListOf<String>()
        for (movie in moviesList) {
            list.add(movie.author)
        }
        return list.distinct()
    }
    fun getDistinctShops():List<String>{
        val list= mutableListOf<String>()
        for (movie in moviesList) {
            list.add(movie.store)
        }
        return list.distinct()
    }
}