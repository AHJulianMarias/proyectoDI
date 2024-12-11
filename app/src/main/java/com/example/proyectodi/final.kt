package com.example.proyectodi

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class final:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val restart = findViewById<TextView>(R.id.tv_restart)

        restart.setOnClickListener{
            val intentRestart = Intent(this,MainActivity::class.java)
            startActivity(intentRestart)
        }

    }



}