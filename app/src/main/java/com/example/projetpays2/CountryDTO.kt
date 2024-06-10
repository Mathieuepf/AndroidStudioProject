package com.example.projetpays2

import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("flags")
    val flags: Flags,
    @SerializedName("capital")
    val capital: String?,
    @SerializedName("population")
    val population: Int
)

data class Name(
    @SerializedName("common")
    val common: String
)

data class Flags(
    @SerializedName("png")
    val png: String
)