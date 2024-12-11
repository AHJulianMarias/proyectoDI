package com.example.proyectodi

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class datosPersonales: AppCompatActivity() {


    lateinit var edTNombre :EditText
    lateinit var edTelefono :EditText
    lateinit var nextButton : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_personales)

        nextButton = findViewById(R.id.nextButton)
        val backMain = findViewById<View>(R.id.backMain)
        nextButton.setOnClickListener{
            edTNombre = findViewById(R.id.editTextNombre)
            edTelefono = findViewById(R.id.editTextTelefono)

            if(!(edTNombre.text.isNullOrEmpty() || edTelefono.text.isNullOrEmpty())){
                if(validCellPhone(edTelefono.text.toString())){
                    val pasarDatosExtra = Intent(this,datosExtra::class.java)
                    pasarDatosExtra.putExtra("nombre",edTNombre.text)
                    pasarDatosExtra.putExtra("telefono",edTelefono.text)
                    startActivity(pasarDatosExtra)
                }else{
                    Toast.makeText(this,"Pon un número de teléfono válido",Toast.LENGTH_LONG).show()
                }


            }else{
                if(edTNombre.text.isNullOrEmpty()){
                    Toast.makeText(this,"Rellena el nombre para pasar al siguiente paso",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Rellena el teléfono para pasar al siguiente paso",Toast.LENGTH_LONG).show()
                }



            }

        }
        backMain.setOnClickListener{

            val backMainInt = Intent(this,MainActivity::class.java)
            startActivity(backMainInt)

        }


    }
    fun validCellPhone(number: String?): Boolean {
        return Patterns.PHONE.matcher(number).matches()
    }


}