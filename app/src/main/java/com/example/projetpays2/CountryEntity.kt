package com.example.projetpays2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey val name: String,
    val flag: String?,
    val capital: String,
    val population: Int
) {
    fun toCountry() = Country(name, flag, capital, population)
}

fun Country.toEntity() = CountryEntity(name, flag, capital, population)
