package com.example.projetpays2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String,
    val flag: String?,
    val capital: String,
    val population: Int
) : Parcelable{
    companion object {
        fun generateCountry(size: Int=10) =
            (1 .. size).map {
                Country(
                    "Country${it}",
                    "default",
                    "Capitol${it}",
                    2
                )
            }
    }

    fun getImage(): String? {
        return flag
    }
}