package com.example.superlist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper (var context: Context): SQLiteOpenHelper(context, "superlist.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table produtos(id integer primary key autoincrement, nome text, quantidade integer, valor decimal)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, anterior: Int, atual: Int) {

    }
}
