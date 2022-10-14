package com.auf.ciscomidtermproject

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.service.autofill.UserData
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.auf.ciscomidtermproject.Fragments.Settings
import java.net.PasswordAuthentication

val DATABASE_NAME = "UserDB"
val TABLE_NAME = "UsersTable"
val COL_NAME = "Name"
val COL_PASS = "Password"
val COL_EMAIL = "Email"
val COL_AGE = "age"
val COL_ID = "id"

class HandlerDatabase(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){


    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME+ "("+
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_NAME + " VARCHAR(256),"+
                COL_EMAIL + " VARCHAR(256),"+
                COL_PASS + " VARCHAR(256),"+
                COL_AGE + " INTEGER)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun instertData(DataBaseHelp: DataBaseHelp){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,DataBaseHelp.Name)
        cv.put(COL_EMAIL,DataBaseHelp.Email)
        cv.put(COL_PASS,DataBaseHelp.Password)
        cv.put(COL_AGE,DataBaseHelp.age)
        var result = db.insert(TABLE_NAME,null,cv)
        if(result == -1.toLong()){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }


    fun readData() : MutableList<DataBaseHelp> {
        var list: MutableList<DataBaseHelp> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val res = db.rawQuery(query, null)


        var idColNum = res.getColumnIndex(COL_ID)
        var nameColNum = res.getColumnIndex(COL_NAME)
        var emailColNum = res.getColumnIndex(COL_EMAIL)
        var ageColNum = res.getColumnIndex(COL_AGE)
        var passwordnum = res.getColumnIndex(COL_PASS)


        if (res.moveToFirst()) {
            do {
                var DBhelp = DataBaseHelp()
                DBhelp.id = res.getString(idColNum).toInt()
                DBhelp.Name = res.getString(nameColNum)
                DBhelp.Email = res.getString(emailColNum)
                DBhelp.age = res.getString(ageColNum).toInt()
                DBhelp.Password = res.getString(passwordnum)
                list.add(DBhelp)
            } while(res.moveToNext())
        }
        res.close()
        db.close()
        return list
    }

    fun updateData(DataBaseHelp: String){
        val db = this.writableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val res = db.rawQuery(query, null)


        var emailColNum = res.getColumnIndex(COL_EMAIL)
        var Password = DataBaseHelp
        if (res.moveToFirst()) {
            do {
                var cv = ContentValues()
                cv.put(COL_EMAIL,res.getString(emailColNum))
                cv.put(COL_PASS,Password)
                db.update(TABLE_NAME,cv, "$COL_EMAIL = ?", arrayOf(res.getString(emailColNum)))
            } while(res.moveToNext())
        }
        res.close()
        db.close()
    }
}