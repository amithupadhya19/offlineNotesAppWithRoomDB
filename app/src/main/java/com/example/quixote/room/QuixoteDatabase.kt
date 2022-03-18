package com.example.quixote.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quixote.models.note
import com.example.quixote.models.user

@Database(version = 3,entities = [user::class,note::class])
@TypeConverters(Converters::class)
abstract class QuixoteDatabase:RoomDatabase() {
    companion object{
        fun get(context: Context):QuixoteDatabase{
            return Room.databaseBuilder(context,QuixoteDatabase::class.java,"QuixoteDatabase").
                allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }
    abstract fun getDao():QuixoteDao
}