package com.auf.ciscomidtermproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DB_NAME = "carDB"
val TBL_NAME = "carTable"
val COLm_carNAME = "carName"
val COLm_carModel = "carModel"
val COLm_carPrice = "carPrice"
val COLm_ID = "carsId"

class HandlerDatabaseCar(var context: Context) : SQLiteOpenHelper(context, DB_NAME,null,1){


    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TBL_NAME+ "("+
                COLm_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLm_carNAME + " VARCHAR(256),"+
                COLm_carModel + " VARCHAR(256),"+
                COLm_carPrice + " VARCHAR(256))"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun instertsData(DataBaseHelpCars: DataBaseHelpCars){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COLm_carNAME,DataBaseHelpCars.carName)
        cv.put(COLm_carModel,DataBaseHelpCars.carModel)
        cv.put(COLm_carPrice,DataBaseHelpCars.carPrice)
        var result = db.insert(TBL_NAME,null,cv)
        if(result == (-1).toLong()){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }


    fun readsData() : MutableList<DataBaseHelpCars> {
        var list: MutableList<DataBaseHelpCars> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM " + TBL_NAME
        val res = db.rawQuery(query, null)


        var idColNum = res.getColumnIndex(COLm_ID)
        var carnameColNum = res.getColumnIndex(COLm_carNAME)
        var carmodelColNum = res.getColumnIndex(COLm_carModel)
        var caroriceColNum = res.getColumnIndex(COLm_carPrice)


        if (res.moveToFirst()) {
            do {
                var DBhelps = DataBaseHelpCars()
                DBhelps.id = res.getString(idColNum).toInt()
                DBhelps.carName = res.getString(carnameColNum)
                DBhelps.carModel = res.getString(carmodelColNum)
                DBhelps.carPrice = res.getString(caroriceColNum)
                list.add(DBhelps)
            } while(res.moveToNext())
        }
        res.close()
        db.close()
        return list
    }
    fun deleteData(carName: String) {
        val db = this.writableDatabase
        db.delete(TBL_NAME, "$COLm_carNAME= ?", arrayOf(carName))
    }
}