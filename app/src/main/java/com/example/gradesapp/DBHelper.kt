package com.example.gradesapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context,"grades.db",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS grades(" +
                "grades_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "lecture_name TEXT," +
                "grade1 INTEGER," +
                "grade2 INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS grades")
        onCreate(db)
    }
}