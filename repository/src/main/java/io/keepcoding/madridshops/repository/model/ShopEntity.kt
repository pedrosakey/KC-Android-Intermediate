package io.keepcoding.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShopEntity(
        var type: Int,
        val id: Long,
        val databaseId: Long,
        val name: String,
        @JsonProperty("description_en") val description: String,
        @JsonProperty("gps_lat") var latitude: String,
        @JsonProperty("gps_lon") var longitude: String,
        val img: String,
        @JsonProperty("logo_img") val logo: String,
        @JsonProperty("opening_hours_en")val openingHours: String,
        val address: String) {

    constructor( id: Long,
                 databaseId: Long,
                 name: String,
                 description: String,
                 latitude: String,
                 longitude: String,
                 img: String,
                 logo: String,
                 openingHours: String,
                 address: String) :
                this(0, id, databaseId, name, description,latitude,longitude,img,logo,openingHours,address)


    // Coordinates come from network with bad format
    fun fixCoordinates() : ShopEntity {
        latitude = saveSafeCoordinate(latitude)
        longitude = saveSafeCoordinate(longitude)
        return this
    }

    private fun saveSafeCoordinate( ramdomCoordinate : String) : String{
        // quitar espacios
        var a = ramdomCoordinate.trim()
        var b = a.substringBefore(',',a)
        return b

    }
  }