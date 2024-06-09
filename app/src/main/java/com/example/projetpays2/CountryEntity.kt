package com.example.projetpays2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey val name: String,
    val flag: String?
) {
    fun toCountry() = Country(name, flag)
}

fun Country.toEntity() = CountryEntity(name, flag)
