package com.example.bugrahan_topal_odev7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bugrahan_topal_odev7.DBs.DB
import org.w3c.dom.Text

class DetailNote : AppCompatActivity() {
    lateinit var db: DB
    lateinit var detailTitle:EditText
    lateinit var detail_View:EditText
    lateinit var btnDelete: Button
    lateinit var btnUpdate: Button
    lateinit var detailDate:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)
        db=DB(this)

        val nid = intent.getIntExtra("nid",-1)
        val title=intent.getStringExtra("title").toString()
        val detail=intent.getStringExtra("detail").toString()
        val date=intent.getStringExtra("date").toString()


        detailTitle=findViewById(R.id.detailTitle)
        detail_View=findViewById(R.id.detail_View)
        btnDelete=findViewById(R.id.btnDelete)
        btnUpdate=findViewById(R.id.btnUpdate)
        detailDate=findViewById(R.id.detailDate)

        detailTitle.setText(title)
        detail_View.setText(detail)
        detailDate.text=date


        btnDelete.setOnClickListener {
            val result=db.deleteNote(nid)
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btnUpdate.setOnClickListener {
            val detailText=detail_View.text.toString()
            val titleText=detailTitle.text.toString()
            val result=db.uptadeNote(nid,titleText,detailText)
            Toast.makeText(applicationContext,"Text is Updateing",Toast.LENGTH_SHORT).show()

        }

    }
}