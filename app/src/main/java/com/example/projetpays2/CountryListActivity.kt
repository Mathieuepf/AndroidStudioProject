package com.example.projetpays2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)


        val countries = Country.generateCountry(10)
        listCountryRecView.adapter = CountryAdapter(countries)

        val userInput = intent.getStringExtra("userInput")

        // Uncomment this line to call displayCountries with userInput
        // displayCountries(userInput)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        println("menu créé")
        menuInflater.inflate(R.menu.list_country, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.button_research_menu -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayCountries(countryName: String?) {
//        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://restcountries.com/")
//            .addConverterFactory(MoshiConverterFactory.create())
//            .client(client)
//            .build()
//
//        val countryService = retrofit.create(CountryService::class.java)
//
//        runBlocking {
//            val countries = countryService.searchCountries(countryName)
//            val adapter = CountryAdapter(countries)
//            listCountryRecView.adapter = adapter
//        }
        TODO("do a research to find the country with the input of the user (=countryName")
    }
}