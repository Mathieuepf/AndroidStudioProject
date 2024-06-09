package com.example.projetpays2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {
    @GET("name/{countryName}")
    fun searchCountries(@Path("countryName") countryName: String?) : Call<List<CountryDTO>>
}