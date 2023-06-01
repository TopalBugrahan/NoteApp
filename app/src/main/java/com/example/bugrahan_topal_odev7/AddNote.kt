package com.example.bugrahan_topal_odev7

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.bugrahan_topal_odev7.DBs.DB
import java.util.*

class AddNote : AppCompatActivity() {
    lateinit var db: DB
    lateinit var btnSave:Button
    lateinit var btnDate:Button
    lateinit var titleEditText:EditText
    lateinit var detailEditText: EditText
    var selectDate=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        db=DB(this)
        btnSave=findViewById(R.id.btnSave)
        btnDate=findViewById(R.id.btnDate)
        titleEditText=findViewById(R.id.titleEditText)
        detailEditText=findViewById(R.id.detailEditText)
        val calender= Calendar.getInstance()
        Log.d("cal",calender.get(Calendar.YEAR).toString())
        btnDate.setOnClickListener {
            val datePickerDialog= DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    selectDate="$i3.${i2+1}.$i"

                },calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.show()
        }

        btnSave.setOnClickListener {
            val title=titleEditText.text.toString()
            val detail=detailEditText.text.toString()
            if(title!=""&&detail!=""&&selectDate!=""){
                Log.d("title",title)
                Log.d("detail",detail)
                Log.d("date",selectDate)
                var result=db.addNote(title,detail,selectDate)
                Log.d("SQLResult",result.toString())
                selectDate=""
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish();
            }
        }
    }
}