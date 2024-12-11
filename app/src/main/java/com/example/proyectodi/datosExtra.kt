package com.example.proyectodi

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class datosExtra : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var spinnerEvento: Spinner
    private lateinit var spinnerCocina: Spinner
    private lateinit var etNumeroAsistentes: EditText
    private lateinit var btnEnviarDatos: Button
    private lateinit var fecha: String
    private lateinit var etNumeroJornadas: EditText
    private lateinit var cbHabitaciones: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mas_datos) // Reemplaza con tu XML
        fecha= "Desconocido"
        // Inicializar vistas
        datePicker = findViewById(R.id.datePicker)
        spinnerEvento = findViewById(R.id.spinner_event)
        spinnerCocina = findViewById(R.id.spinner_cuisine)
        etNumeroAsistentes = findViewById(R.id.editText_attendees)
        btnEnviarDatos = findViewById(R.id.button_submit)
        val backMain = findViewById<View>(R.id.backMain)
        val backPerso = findViewById<View>(R.id.backPersonales)
        etNumeroJornadas = findViewById(R.id.editText_jornadas)
        cbHabitaciones = findViewById(R.id.checkBox_habitaciones)

        // Configuración del DatePicker
        datePicker.setOnClickListener {
            mostrarDatePicker()
        }

        // Configurar Spinners
        configurarSpinner(spinnerEvento, listOf("Banquete", "Jornada", "Congreso"))
        configurarSpinner(spinnerCocina, listOf("Bufé", "Carta", "Pedir cita con el chef", "No precisa"))

        spinnerEvento.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                if (selectedItem == "Congreso") {
                    etNumeroJornadas.visibility = View.VISIBLE
                    cbHabitaciones.visibility = View.VISIBLE
                } else {
                    etNumeroJornadas.visibility = View.GONE
                    cbHabitaciones.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        // Configuración del botón
        btnEnviarDatos.setOnClickListener {
            enviarDatos()
        }
        backPerso.setOnClickListener{

            val backPersoInt = Intent(this,datosPersonales::class.java)
            startActivity(backPersoInt)


        }
        backMain.setOnClickListener{
            val backMainInt = Intent(this,MainActivity::class.java)
            startActivity(backMainInt)


        }

    }

    // Método para mostrar el DatePicker
    private fun mostrarDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            fecha = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        }, year, month, day)

        datePickerDialog.show()
    }

    // Método para configurar Spinners
    private fun configurarSpinner(spinner: Spinner, opciones: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    // Método para capturar y mostrar los datos ingresados
    private fun enviarDatos() {
        val tipoEvento = spinnerEvento.selectedItem.toString()
        val tipoCocina = spinnerCocina.selectedItem.toString()
        val numeroAsistentes = etNumeroAsistentes.text.toString()
        val numeroJornadas = etNumeroJornadas.text.toString()
        val necesitaHabitaciones = cbHabitaciones.isChecked

        if (fecha.isEmpty() || numeroAsistentes.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        val mensaje = StringBuilder()
        mensaje.append("Fecha: $fecha\n")
        mensaje.append("Tipo de evento: $tipoEvento\n")
        mensaje.append("Número de asistentes: $numeroAsistentes\n")
        mensaje.append("Tipo de cocina: $tipoCocina\n")

        if (tipoEvento == "Congreso") {
            if (numeroJornadas.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa el número de jornadas", Toast.LENGTH_SHORT).show()
                return
            }
            mensaje.append("Número de jornadas: $numeroJornadas\n")
            mensaje.append("¿Se necesitan habitaciones?: ${if (necesitaHabitaciones) "Sí" else "No"}\n")
        }
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
            val intFinal = Intent(this,final::class.java)
            startActivity(intFinal)


    }
}
