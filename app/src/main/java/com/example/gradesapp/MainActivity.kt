package com.example.gradesapp

import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gradesapp.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() {
    private lateinit var design : ActivityMainBinding
    private lateinit var rvAdapter: RVAdapter
    private lateinit var gradeList: ArrayList<Grades>
    private lateinit var dbHelper: DBHelper
    private  var average: Int =0
    private  var total: Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_main)
        copyDatabase()
        dbHelper= DBHelper(this)

        gradeList = Gradesdao().getAllGrades(dbHelper)


        for (i in gradeList){
            total+=(i.grade1 + i.grade2)/2
        }
        average=total/gradeList.size


        design.toolbarMain.title ="Grades"
        design.toolbarMain.subtitle= "Average: ${average}"
        setSupportActionBar(design.toolbarMain)

        design.recyclerView.setHasFixedSize(true)
        design.recyclerView.layoutManager= LinearLayoutManager(this)

        rvAdapter= RVAdapter(this,gradeList)
        design.recyclerView.adapter= rvAdapter

        design.floatingActionButtonAddGrade.setOnClickListener {
            startActivity(Intent(this@MainActivity,RegistryActivity::class.java))
        }



    }

    fun copyDatabase(){
        val dpc = DatabaseCopyHelper(this)
        try {
            dpc.createDataBase()
        }catch (e : SQLiteException){
            Error("Unable to create database")
        }

        try {
            dpc.openDataBase()
        }catch (e : SQLiteException){
            Error("Unable to open database")
        }
    }
}