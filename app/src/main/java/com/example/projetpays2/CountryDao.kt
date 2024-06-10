package com.example.projetpays2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: CountryEntity)

    @Query("SELECT * FROM countries WHERE name = :name")
    suspend fun getCountry(name: String): CountryEntity?

    @Query("SELECT * FROM countries WHERE capital = :capital")
    fun getCountryByCapital(capital: String?): CountryEntity?
}