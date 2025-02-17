package com.example.parcanbiluygulamasi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeritabaniYardimcisi(context: Context) : SQLiteOpenHelper(context,"parcalar.sqlite",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {


        db?.execSQL("CREATE TABLE IF NOT EXISTS \"parcalar\" (\n" +
                "    \"parca_id\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    \"parca_adi\" TEXT,\n" +
                "    \"parca_resim\" TEXT\n" +
                ");\n")



    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS parcalar")
        onCreate(db)

    }

}