package com.example.projetpays2

import retrofit2.http.GET
import retrofit2.http.Query

interface CountryService {
    @GET("api")
    suspend fun getCountries(@Query("results") nb : Int = 20) : CountriesResult
}

data class CountriesResult(val results: List<Country>)
data class Country(val name: String, val flag: Int)