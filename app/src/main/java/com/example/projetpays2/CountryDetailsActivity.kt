package com.example.projetpays2

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

private const val TAG = "CountryDetailsActivity"

class CountryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_country_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val countryName = intent.getStringExtra("countryName")
        Log.d(TAG,"Country name : ${countryName}")
        val countryFlag = intent.getStringExtra("countryFlag")
        Log.d(TAG,"Country flag : ${countryFlag}")
        val countryCapital = intent.getStringExtra("countryCapital")
        Log.d(TAG,"Country capital : ${countryCapital}")
        val countryPopulation = intent.getIntExtra("countryPopulation", 0)
        Log.d(TAG,"Country population : ${countryPopulation}")

        val countryNameTextView = findViewById<TextView>(R.id.country_name_details_country_activity)
        val countryFlagImageView = findViewById<ImageView>(R.id.country_flag_country_details_activity)
        val countryCapitalTextView = findViewById<TextView>(R.id.country_capital_details_country_activity)
        val countryPopulationTextView = findViewById<TextView>(R.id.country_population_details_country_activity)

        countryNameTextView.text = countryName
        Glide.with(this).load(countryFlag).into(countryFlagImageView)
        countryCapitalTextView.text = "Capital: $countryCapital"
        countryPopulationTextView.text = "Population: $countryPopulation"
    }
}
