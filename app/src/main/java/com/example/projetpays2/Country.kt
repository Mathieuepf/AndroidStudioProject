package com.example.projetpays2

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String,
    val flag: String?
) : Parcelable{
    companion object {
        fun generateCountry(size: Int=10) =
            (1 .. size).map {
                Country(
                    "Country${it}",
                    "default"
                )
            }
    }

    fun getImage() = R.drawable.unknown_flag2
}

//data class CountryDTO(
//    @SerializedName("name")
//    val name: Name,
//    @SerializedName("region")
//    val region: String,
//    @SerializedName("area")
//    val area: Double,
//    @SerializedName("population")
//    val population: Int
//)
//
//data class Name(
//    @SerializedName("common")
//    val common: String
//)