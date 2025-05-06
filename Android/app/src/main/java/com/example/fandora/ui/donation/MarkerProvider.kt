package com.example.fandora.ui.donation

import com.example.fandora.data.model.common.MarkerLocation
import com.google.android.gms.maps.model.LatLng

object MarkerProvider {
    fun getCompanyMarker(): List<MarkerLocation> {
        return listOf(
            MarkerLocation("MusicSmile Foundation", LatLng(37.624240, 127.087915)),
            MarkerLocation("Idol Dream Center", LatLng(37.620925, 127.081022)),
            MarkerLocation("MusicBridge", LatLng(37.625773, 127.094862)),
            MarkerLocation("SoulHarmony", LatLng(37.623949, 127.088419)),
            MarkerLocation("Green Eco Store", LatLng(37.624224, 127.091708)),
            MarkerLocation("Grow Care Center", LatLng(37.623591, 127.090252))
            )
    }
}