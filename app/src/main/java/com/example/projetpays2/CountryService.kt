package com.example.projetpays2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {
    @GET("name/{countryName}")
    fun searchCountries(@Path("countryName") countryName: String?) : Call<List<CountriesResult>>
}



data class CountriesResult(
    val name: Name
) {
    fun toModel() = Country(
        name = name.common,
        flag = "default"
    )
}

data class Name(
    val common: String
)
//data class Country(val name: String, val flag: String)