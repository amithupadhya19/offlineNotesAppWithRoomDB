package com.example.quixote

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.quixote.models.globalclass
import com.example.quixote.models.note
import com.example.quixote.room.QuixoteDatabase
import com.github.drjacky.imagepicker.ImagePicker
import java.net.URI

var imageField:ImageView?=null
var image:Bitmap?=null
class addNoteDialogFragment(context: Context,val user:String):DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootview:View=inflater.inflate(R.layout.add_note_dialog,container,false)
        val titlefield:EditText=rootview.findViewById(R.id.titleEdittext)
        val detailField:EditText=rootview.findViewById(R.id.detailEdittext)
         imageField=rootview.findViewById(R.id.chooseImage)
        val imagepickertext:TextView=rootview.findViewById(R.id.imagepickertv)
        val addNoteButton:Button=rootview.findViewById(R.id.addNoteDialogButton)
        image=null
        val dao=QuixoteDatabase.get(requireContext()).getDao()
        imagepickertext.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .cropOval()
                .compress(1024)
                .maxResultSize(1080,1080)
                .start()


            //val dialog=chooseImageDialogFragment(imageField!!)

            //dialog.show(requireFragmentManager(),"choose Image")
        }

        addNoteButton.setOnClickListener {
            if (titlefield.text.isEmpty()){
                titlefield.requestFocus()
                titlefield.setError("please enter title")
                return@setOnClickListener
            }
            if (detailField.text.isEmpty()){
                detailField.requestFocus()
                detailField.setError("please enter detail")
                return@setOnClickListener
            }
            if (image==null){
                Toast.makeText(context,"please choose an image",Toast.LENGTH_SHORT).show()
            }else{
                val title:String=titlefield.text.toString()
                val details:String=detailField.text.toString()
                val note=note(title,details, image!!, user)
                dao.insertNote(note)
                globalclass().addnote(note)
                Toast.makeText(context,"note added",Toast.LENGTH_SHORT).show()
                titlefield.setText("")
                detailField.setText("")
                globalclass().getadapter().notifyDataSetChanged()
                dismiss()
            }
        }

        return rootview
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uri: Uri? = data?.data
        imageField?.setImageURI(uri)
        val imageDrawable= imageField?.drawable as BitmapDrawable
        image=imageDrawable.bitmap
    }
}