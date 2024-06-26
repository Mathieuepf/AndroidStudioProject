package com.example.projetpays2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch

private const val TAG = "CountryListActivity"

class CountryListActivity : AppCompatActivity() {

    private lateinit var listCountryRecView: RecyclerView
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_country_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "countries-db").build()

        Log.d(TAG,"Room database built")

        listCountryRecView = findViewById(R.id.list_countries_recyclerView)
        listCountryRecView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //val countries = Country.generateCountry(10)
        //listCountryRecView.adapter = CountryAdapter(countries, this, db)

        val userInput = intent.getStringExtra("userInput")
        Log.d("CountryListActivity", if(userInput.isNullOrEmpty()) "rien" else userInput)
        displayCountries(userInput)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG,"menu créé")
        menuInflater.inflate(R.menu.list_country, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.button_research_menu -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayCountries(countryName: String?) {
        val countryRepository = CountryRepositoryImpl()
        lifecycleScope.launch {
            val countries = countryRepository.searchCountries(countryName ?: "")
            Log.d("CountryListActivity", countries.toString())
            if(countries.isNotEmpty()){
                listCountryRecView.adapter = CountryAdapter(countries, this@CountryListActivity, db)
            } else {
                val localCountries: List<Country> = if (countryName != null && countryName != "") {
                    db.countryDao().getCountry(countryName)?.let { listOf(it.toCountry()) }
                        ?: emptyList()
                } else {
                    db.countryDao().getCountries()?.map { it.toCountry() } ?: emptyList()
                }
                if (localCountries.isNotEmpty()) {
                    listCountryRecView.adapter =
                        CountryAdapter(localCountries, this@CountryListActivity, db)
                } else {
                    val generatedCountries = Country.generateCountry(10)
                    listCountryRecView.adapter =
                        CountryAdapter(generatedCountries, this@CountryListActivity, db)
                }
            }
        }
    }
}
