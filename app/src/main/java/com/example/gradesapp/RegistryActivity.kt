package com.example.gradesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.gradesapp.databinding.ActivityRegistryBinding

class RegistryActivity : AppCompatActivity() {
    private lateinit var design : ActivityRegistryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design= DataBindingUtil.setContentView(this,R.layout.activity_registry)

        design.toolbarRegistry.title ="New Registry"
        setSupportActionBar(design.toolbarRegistry)



        val dbHelper= DBHelper(this)

        design.buttonSave.setOnClickListener {
            val lecture_name= design.editTextLectureName.text.toString().trim()
            val grade1= design.editTextGrade1.text.toString().toInt()
            val grade2= design.editTextGrade2.text.toString().toInt()
            inputControl()
            val inputControl =inputControl()
            if (inputControl){
                Gradesdao().addGrade(dbHelper,lecture_name.toString(),grade1,grade2)
                startActivity(Intent(this@RegistryActivity,MainActivity::class.java))
                finish()
            }else
                return@setOnClickListener
        }

    }

    private fun inputControl() : Boolean{
        val lecture_name= design.editTextLectureName.text.toString().trim()
        val grade1= design.editTextGrade1.text.toString().toInt()
        val grade2= design.editTextGrade2.text.toString().toInt()


        if (TextUtils.isEmpty(lecture_name)){
            Toast.makeText(applicationContext,"Lecture name is empty.",Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(grade1.toString())){
            Toast.makeText(applicationContext,"Lecture name is empty.",Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(grade2.toString())){
            Toast.makeText(applicationContext,"Lecture name is empty.",Toast.LENGTH_SHORT).show()
            return false
        }
        if (grade1<0 ||grade1>100 || grade2<0 ||grade2>100){
            Toast.makeText(applicationContext,"Grade must be between 0 to 100.",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}