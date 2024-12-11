package com.example.proyectodi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val nextButton = findViewById<ImageButton>(R.id.nextButton)


        nextButton.setOnClickListener{
            val pasoPersonales = Intent(this,datosPersonales::class.java)
            startActivity(pasoPersonales)

        }



    }
}