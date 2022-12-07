package com.example.gradesapp

import android.content.ContentValues
import android.text.Editable

class Gradesdao {

    fun getAllGrades(dbHelper: DBHelper) : ArrayList<Grades>{

        val gradeList = ArrayList<Grades>()
        val db= dbHelper.writableDatabase
        val cursor = db.rawQuery("SELECT*FROM grades",null)

        while (cursor.moveToNext()){
            val grade = Grades(
                cursor.getInt(cursor.getColumnIndexOrThrow("grade_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("lecture_name")),
                cursor.getInt(cursor.getColumnIndexOrThrow("grade1")),
                cursor.getInt(cursor.getColumnIndexOrThrow("grade2"))
            )
            gradeList.add(grade)
        }
        return gradeList
    }

    fun addGrade(dbHelper: DBHelper, lecture_name:String, grade1:Int, grade2:Int){
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put("lecture_name",lecture_name)
        values.put("grade1",grade1)
        values.put("grade2",grade2)

        db.insertOrThrow("grades",null,values)
        db.close()


    }
    fun deleteGrade(dbHelper: DBHelper,grade_id:Int){
        val db = dbHelper.writableDatabase
        db.delete("grades","grade_id=?", arrayOf(grade_id.toString()))
        db.close()
    }

    fun updateGrade(dbHelper: DBHelper,grade_id:Int,lecture_name:String,grade1:Int,grade2:Int){
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put("lecture_name",lecture_name)
        values.put("grade1",grade1)
        values.put("grade2",grade2)
        db.update("grades",values,"grade_id=?", arrayOf(grade_id.toString()))
        db.close()

    }

}