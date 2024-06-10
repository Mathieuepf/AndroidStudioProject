package com.example.projetpays2

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class PositionDTO(
    @SerializedName("latlng")
    val pos: List<Double>
){
    companion object{
        fun toInit() = PositionDTO(
            doubleArrayOf(40.0, 2.0).toList()
        )
    }
}