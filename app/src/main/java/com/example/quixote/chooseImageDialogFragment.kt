package com.example.quixote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class chooseImageDialogFragment( val imageview: ImageView):DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootview:View=inflater.inflate(R.layout.choose_image_layout,container,false)

        val camera:TextView=rootview.findViewById(R.id.camera)
        val gallery:TextView=rootview.findViewById(R.id.gallery)

        camera.setOnClickListener {

        }

        return rootview
    }
}