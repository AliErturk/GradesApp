package com.example.gradesapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val context: Context,private val gradeList :List<Grades>) :
                RecyclerView.Adapter<RVAdapter.CardViewHolder>() {

    inner class CardViewHolder(view :View) : RecyclerView.ViewHolder(view){

        var textViewLecture :TextView
        var textViewGrade1 : TextView
        var textViewGrade2 : TextView
        var cardViewGrade : CardView

        init {
            textViewLecture = view.findViewById(R.id.textViewLecture)
            textViewGrade1 = view.findViewById(R.id.textViewGrade1)
            textViewGrade2 = view.findViewById(R.id.textViewGrade2)
            cardViewGrade = view.findViewById(R.id.cardViewGrade)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val design  = LayoutInflater.from(context).inflate(R.layout.card_design,parent,false)
        return CardViewHolder(design)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val grade = gradeList[position]
        holder.textViewLecture.text = grade.lecture_name
        holder.textViewGrade1.text= grade.grade1.toString()
        holder.textViewGrade2.text= grade.grade2.toString()
        holder.cardViewGrade.setOnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("grade",grade)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return gradeList.size
    }
}