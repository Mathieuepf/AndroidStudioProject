package com.example.projetpays2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val researchButton = findViewById<Button>(R.id.button_research_main_activity)
        val nameLand = findViewById<EditText>(R.id.nom_pays_editText)

        researchButton.setOnClickListener {
            val countryName = nameLand.text

            val intent = Intent(this, CountryListActivity::class.java)
            intent.putExtra("userInput", countryName)
            startActivity(intent)
        }
    }
}