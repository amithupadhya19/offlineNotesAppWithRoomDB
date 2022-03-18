package com.example.quixote.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quixote.Home
import com.example.quixote.R
import com.example.quixote.models.globalclass
import com.example.quixote.models.note
import com.example.quixote.room.QuixoteDao
import com.example.quixote.room.QuixoteDatabase


class noteAdapter(private  val notes:List<note>,val context: Context):RecyclerView.Adapter<noteAdapter.noteViewholder>() {

    val dao=QuixoteDatabase.get(context).getDao()
    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteViewholder {
        val itemview=LayoutInflater.from(parent.context).inflate(R.layout.note,parent,false)
        return noteViewholder(itemview,mListener)
    }

    override fun onBindViewHolder(holder: noteViewholder, position: Int) {
            val currentItem=notes[position]
            holder.imageView.setImageResource(R.drawable.delete)
            holder.imageView.setOnClickListener {
                dao.delete_note(notes[position])
                globalclass().deletenote(notes[position])
                notifyDataSetChanged()
            }
            holder.textView.text=currentItem.task.toString()
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class noteViewholder(itemview: View,listener: onItemClickListener):RecyclerView.ViewHolder(itemview){
            val imageView:ImageView=itemview.findViewById(R.id.delete)
            val textView:TextView=itemview.findViewById(R.id.taskTitle)
            init {
                itemview.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }
            }
    }
}