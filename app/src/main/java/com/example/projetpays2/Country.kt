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

    fun getImage(): String? {
        return flag
    }
}