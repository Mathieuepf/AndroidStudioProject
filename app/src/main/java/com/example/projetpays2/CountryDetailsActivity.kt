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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private const val TAG = "CountryDetailsActivity"

class CountryDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var pos: LatLng
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

        val map = supportFragmentManager.findFragmentById(R.id.country_details_map) as SupportMapFragment
        pos = LatLng(40.0, 2.0)
        map.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        // Ajouter un marqueur à une position et déplacer la caméra
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(pos))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos))
    }
}
