package com.example.quixote.room

import androidx.room.*
import com.example.quixote.models.note
import com.example.quixote.models.user

@Dao
interface QuixoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: user)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: note)

    @Query("select exists(select * from user where name=:username or email=:email or phone=:phone)")
    fun is_taken(email:String,username:String,phone:String):Boolean

    @Query("select exists(select * from user where (email=:input or phone=:input) and password=:password)")
    fun is_valid(input:String,password:String):Boolean

    @Query("select name from user where email=:input or phone=:input")
    fun get_user(input:String?):String

    @Query("select * from note where user=:user")
    fun get_note(user:String):note

    @Query("select * from note where user=:user order by id desc")
    fun get_notes(user:String):List<note>

    @Delete
    fun delete_note(note: note)
}