package com.example.bugrahan_topal_odev7.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.marginBottom
import com.example.bugrahan_topal_odev7.R
import com.example.bugrahan_topal_odev7.models.Note

class NoteAdapter(private val context:Activity,private val noteList:List<Note>):ArrayAdapter<Note>(context, R.layout.note_list_view,noteList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView=context.layoutInflater.inflate(R.layout.note_list_view,null)

        val title=rootView.findViewById<TextView>(R.id.itemTitle)
        val detail=rootView.findViewById<TextView>(R.id.itemDetail)
        val date=rootView.findViewById<TextView>(R.id.itemDate)

        var note=noteList.get(position)

        title.text=note.title
        detail.text=note.detail
        date.text=note.date


        return rootView
    }
}