package com.example.bugrahan_topal_odev7.DBs

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bugrahan_topal_odev7.models.Note

class DB(private val context: Context) : SQLiteOpenHelper(context, DBName,null, DBVersion){

    companion object{
        val DBName="note"
        val DBVersion=1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createNoteTable="CREATE TABLE \"note\" (\n" +
                "\t\"nid\"\tINTEGER,\n" +
                "\t\"title\"\tTEXT,\n" +
                "\t\"detail\"\tTEXT,\n" +
                "\t\"date\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nid\" AUTOINCREMENT)\n" +
                ")"
        p0?.execSQL(createNoteTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropTable="DROP TABLE IF EXISTS note"
        p0?.execSQL(dropTable)
        onCreate(p0)
    }

    fun addNote(title:String,detail:String,date:String):Long{
        val db=this.writableDatabase
        val values=ContentValues()
        values.put("title",title)
        values.put("detail",detail)
        values.put("date",date)
        val effectRow=db.insert("note",null,values)
        db.close()
        return effectRow
    }
    fun allData():MutableList<Note>{
        val db=this.readableDatabase
        val cursor=db.query("note",null,null,null,null,null,null,null,)
        val allNotes= mutableListOf<Note>()
        while (cursor.moveToNext()){
            val nid=cursor.getInt(0)
            val title = cursor.getString(1)
            val detail= cursor.getString(2)
            val date=cursor.getString(3)
            allNotes.add(Note(nid,title,detail,date))
        }
        db.close()
        return allNotes
    }

    fun deleteNote(nid:Int):Int{
        val db = this.writableDatabase
        val status=db.delete("note","nid=${nid}",null)
        db.close()
        return status
    }

    fun uptadeNote(nid:Int,title:String,detail: String):Int{
        val db = this.writableDatabase
        val content=ContentValues()
        content.put("title",title)
        content.put("detail",detail)
        val status= db.update("note",content,"nid=${nid}",null)
        db.close()
        return status
    }
}