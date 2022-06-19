package com.example.firebasenotepad.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    var name:String="",
    var author:String="",
    var store:String="",
    var completed:Boolean = false
):Parcelable
