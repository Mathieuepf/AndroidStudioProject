package com.example.projetpays2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
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

    lateinit var listCountryRecView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_country_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        System.out.println("On est là")

        listCountryRecView =
            findViewById<RecyclerView>(R.id.list_countries_recyclerView)

        listCountryRecView.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)


        val countries = Country.generateClountry(10)
        listCountryRecView.adapter = CountryAdapter(countries)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        System.out.println("menu créé")
        menuInflater.inflate(R.menu.list_country, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.button_research_menu -> {
                displayCountries()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayCountries() {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        val countryService = retrofit.create(CountryService::class.java)

        runBlocking {
            val countries = countryService.getCountries(10)
            val countryList = countries.results.map {
                Country(
                    it.name, it.flag
                )
            }
            val adapter = CountryAdapter(countryList)
            listCountryRecView.adapter = adapter
        }
    }
}