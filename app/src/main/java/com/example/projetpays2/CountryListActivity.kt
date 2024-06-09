package com.example.projetpays2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CountryListActivity : AppCompatActivity() {

    private lateinit var listCountryRecView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_country_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listCountryRecView =
            findViewById<RecyclerView>(R.id.list_countries_recyclerView)

        listCountryRecView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val userInput = intent.getStringExtra("userInput")
        displayCountries(userInput)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        println("menu créé")
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
        runBlocking {
            try {
                val countries = withContext(Dispatchers.IO) {
                    countryRepository.searchCountries(countryName ?: "")
                }
                listCountryRecView.adapter = CountryAdapter(countries)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this@CountryListActivity,
                    "Failed to fetch countries",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}