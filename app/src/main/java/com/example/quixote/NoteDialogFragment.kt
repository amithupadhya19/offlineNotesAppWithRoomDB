package com.example.quixote

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.text.FieldPosition

class NoteDialogFragment(val title:String,val image:Bitmap,val detail:String):DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootview:View=inflater.inflate(R.layout.note_dialog,container,false)
        rootview.findViewById<TextView>(R.id.noteTitle).text=title
        rootview.findViewById<ImageView>(R.id.noteImage).setImageBitmap(image)
        rootview.findViewById<TextView>(R.id.noteDetail).text=detail
        return rootview
    }
 }