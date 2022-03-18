package com.example.quixote.models

import android.provider.ContactsContract
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class user(
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "email")
    val email:String,
    @ColumnInfo(name = "phone")
    val phone:String,
    @ColumnInfo(name = "password")
    val password:String
){
    @PrimaryKey(autoGenerate = true)
     var id:Int=0
}