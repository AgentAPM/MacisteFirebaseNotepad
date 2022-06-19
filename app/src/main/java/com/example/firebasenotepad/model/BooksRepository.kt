package com.example.firebasenotepad.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebasenotepad.model.entities.Book
import com.google.firebase.database.FirebaseDatabase


object BooksRepository {
    private val databaseURL = "https://mobintmaciste-default-rtdb.europe-west1.firebasedatabase.app/"
    private val BooksURL get()="users/${AuthenticationModel.userUUID}}/books"

    private var useCache = false
            fun invalidateCache(){useCache=false}

    private val _booksList = MutableLiveData<MutableList<Book>>()
            val booksListRef:LiveData<MutableList<Book>> get()= _booksList
            val booksList:MutableList<Book> get()= _booksList.value!!

    fun loadBooks(){
        if(useCache){
            //Set value anyways to trigger observers
            _booksList.value= _booksList.value
            return
        }
        if(AuthenticationModel.userUUID==null) return

        val task = FirebaseDatabase.getInstance(databaseURL).getReference("$BooksURL/value").get()
        task.addOnSuccessListener { it ->
            //Log.d("dbg","$it")
            val list: MutableList<Book> = mutableListOf()
            it.children.forEach{
                list.add(it.getValue(Book::class.java)!!)
            }
            //Log.d("dbg","$list")
            _booksList.value = list
        }
        task.addOnFailureListener { it->
            Log.d("dbg","Firebase fetch failed. Message = ${it.message}")
        }
        useCache = true
    }
    fun saveBooks(){
        if(AuthenticationModel.userUUID==null) return

        val task = FirebaseDatabase.getInstance(databaseURL).getReference(BooksURL).setValue(booksListRef)

        useCache = false
    }

    fun getDistinctAuthors():List<String>{
        val list= mutableListOf<String>()
        for (book in booksList) {
            list.add(book.author)
        }
        return list.distinct()
    }
    fun getDistinctShops():List<String>{
        val list= mutableListOf<String>()
        for (book in booksList) {
            list.add(book.store)
        }
        return list.distinct()
    }
}