package com.example.quixote.models

import com.example.quixote.adapters.noteAdapter
import com.example.quixote.room.QuixoteDatabase


lateinit var notes:ArrayList<note>
lateinit var adapter: noteAdapter

class globalclass {
    public fun getnotes():ArrayList<note>{
        return notes
    }
    public fun getadapter():noteAdapter{
        return adapter
    }
    public fun addnote(note:note){
        notes.add(0,note)
    }
    public fun setadapter(thisadapter:noteAdapter){
        adapter=thisadapter
    }

    public fun setnotes(thisnotes:ArrayList<note>){
        notes=thisnotes
    }

    public fun deletenote(note:note){
        notes.remove(note)
    }
}