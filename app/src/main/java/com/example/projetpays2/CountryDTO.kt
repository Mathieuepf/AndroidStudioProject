package com.example.projetpays2

import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("name")
    val name: Name,
    @SerializedName("flags")
    val flags: Flags
)

data class Name(
    @SerializedName("common")
    val common: String
)

data class Flags(
    @SerializedName("png")
    val png: String
)