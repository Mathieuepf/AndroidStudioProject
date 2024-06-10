package com.example.projetpays2

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CountryRepository {
    suspend fun searchCountries(query: String): List<Country>

    suspend fun searchPosition(query: String): PositionDTO
}



class CountryRepositoryImpl: CountryRepository {

    //private val BASE_URL = "https://restcountries.com/v3.1/"
    private val BASE_URL = "https://apicountries.com/"

    private val countryService: CountryService

    init {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()

        countryService = retrofit.create(CountryService::class.java)
    }



    override suspend fun searchCountries(query: String): List<Country> {
        return try {
            Log.d("searchCountries", "success !")
            val response = countryService.searchCountries(query)
            Log.d("searchCountries", "success ! 2")
            if (response.isSuccessful) {
                val countriesDTO = response.body() ?: emptyList()
                countriesDTO.map { it.toModel() }
            } else {
                emptyList()
            }
        } catch (exception: Exception) {
            Log.d("searchCountries", exception.message.toString())
            emptyList()
        }
    }

    override suspend fun searchPosition(query: String): PositionDTO{
        return try{
            val response = countryService.searchPosition(query)
            if(response.isSuccessful){
                val position = response.body()
                Log.d("CountryRepository", position.toString())
                position!!
            }else{
                PositionDTO.toInit()
            }
        }catch (e: Exception){
            Log.d("CountryRepository", e.message.toString())
            PositionDTO.toInit()
        }
    }

}

fun CountryDTO.toModel(): Country {
    var capitalName = ""
    if(capital.isNullOrEmpty()){
        capitalName = "Unknown"
    }else{
        capitalName = capital
    }
    return Country(name, flags.png, capitalName, population)
}