package com.example.gradesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.gradesapp.databinding.ActivityDetailBinding
import com.example.gradesapp.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var design : ActivityDetailBinding
    private lateinit var dbHelper: DBHelper
    private lateinit var grade : Grades
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design= DataBindingUtil.setContentView(this,R.layout.activity_detail)

        design.toolbarDetail.title= "Grade Detail"
        setSupportActionBar(design.toolbarDetail)
        grade = intent.getSerializableExtra("grade") as Grades
        dbHelper= DBHelper(this)

        design.editTextLectureDetail.setText(grade.lecture_name)
        design.editTextG1Detail.setText(grade.grade1.toString())
        design.editTextG2Detail.setText(grade.grade2.toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_delete ->{
                Gradesdao().deleteGrade(dbHelper,grade.grade_id)
                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }
            R.id.action_update ->{
                val lecture_name=design.editTextLectureDetail.text.toString().trim()
                val grade1 = design.editTextG1Detail.text.toString().toInt()
                val grade2 = design.editTextG2Detail.text.toString().toInt()
                inputControl()
                val inputControl =inputControl()
                if (inputControl){
                    Gradesdao().updateGrade(dbHelper,grade.grade_id,lecture_name,grade1,grade2)
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                    finish()
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }
    private fun inputControl() : Boolean{
        val lecture_name= design.editTextLectureDetail.text.toString().trim()
        val grade1= design.editTextG1Detail.text.toString().toInt()
        val grade2= design.editTextG2Detail.text.toString().toInt()


        if (TextUtils.isEmpty(lecture_name)){
            Toast.makeText(applicationContext,"Lecture name is empty.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(grade1.toString())){
            Toast.makeText(applicationContext,"Lecture name is empty.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(grade2.toString())){
            Toast.makeText(applicationContext,"Lecture name is empty.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (grade1<0 ||grade1>100 || grade2<0 ||grade2>100){
            Toast.makeText(applicationContext,"Grade must be between 0 to 100.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}