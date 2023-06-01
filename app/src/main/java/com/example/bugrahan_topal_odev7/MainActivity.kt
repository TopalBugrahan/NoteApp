package com.example.bugrahan_topal_odev7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.core.view.marginBottom
import com.example.bugrahan_topal_odev7.DBs.DB
import com.example.bugrahan_topal_odev7.adapters.NoteAdapter
import com.example.bugrahan_topal_odev7.models.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var db: DB
    lateinit var noteListView:ListView
    lateinit var btnAdd:FloatingActionButton
    lateinit var allNotes:MutableList<Note>
    lateinit var noteAdapter:NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db=DB(this)
        Log.d("onCreate","onCreate")
        noteListView=findViewById(R.id.noteListView)
        btnAdd=findViewById(R.id.btnAdd)

        allNotes=db.allData()
        Log.d("Main",allNotes.toString())
        noteAdapter=NoteAdapter(this,allNotes)
        noteListView.adapter=noteAdapter

        noteListView.setOnItemClickListener { adapterView, view, i, l ->
            val detail= allNotes.get(i)
            val intent=Intent(this,DetailNote::class.java)

            intent.putExtra("nid",detail.nid)
            intent.putExtra("title",detail.title)
            intent.putExtra("detail",detail.detail)
            intent.putExtra("date",detail.date)
            startActivity(intent)
        }

        btnAdd.setOnClickListener {
            val intent=Intent(this,AddNote::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        Log.d("onRestart","onRestart")
        allNotes.clear()
        val newdata=db.allData()
        for(note in newdata){
            allNotes.add(note)
        }

        noteAdapter.notifyDataSetChanged()
        super.onRestart()
    }


}