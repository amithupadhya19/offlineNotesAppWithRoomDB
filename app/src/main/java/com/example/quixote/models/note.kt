package com.example.quixote.models

import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class note(
    @ColumnInfo(name = "task")
    val task:String,
    @ColumnInfo(name = "details")
    val details:String,
    @ColumnInfo(name = "image")
    val image:Bitmap,
    @ColumnInfo(name="user")
    val user:String
){
    @PrimaryKey(autoGenerate = true)
     var id:Int=0
 }