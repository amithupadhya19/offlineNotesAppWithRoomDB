package com.example.quixote

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quixote.adapters.noteAdapter
import com.example.quixote.models.globalclass
import com.example.quixote.models.note
import com.example.quixote.room.Converters
import com.example.quixote.room.QuixoteDatabase


class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val textView:TextView=findViewById(R.id.tvusername)
        val dao=QuixoteDatabase.get(this).getDao()
        val input=intent.getStringExtra("user")
        val user=dao.get_user(input)
        textView.text=user
        var notes=dao.get_notes(user)
        globalclass().setnotes(notes as ArrayList<note>)
        val recyclerView:RecyclerView=findViewById(R.id.recyclerview)
        val adapter=noteAdapter(globalclass().getnotes(),this)
        globalclass().setadapter(adapter)
        recyclerView.adapter=globalclass().getadapter()
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        adapter.setOnItemClickListener(object : noteAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
               val dialog= NoteDialogFragment(notes[position].task,notes[position].image,notes[position].details)
                dialog.show(supportFragmentManager,"note")
            }
        })

        val addNoteButton:Button=findViewById(R.id.addNoteButton)
        addNoteButton.setOnClickListener {
            val dialogAdd=addNoteDialogFragment(this,user)
            dialogAdd.show(supportFragmentManager,"add note")
        }

        val sharedPreferences: SharedPreferences =getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()

        val logoutButton:ImageView=findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            val intent= Intent(this,Login::class.java)
            editor.clear().commit()
            startActivity(intent)
            finish()
        }

    }
}