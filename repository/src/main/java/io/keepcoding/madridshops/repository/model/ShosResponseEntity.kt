package io.keepcoding.madridshops.repository.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShopsResponseEntity(
      val result: List<ShopEntity>
) {
    fun insertType(type: Int) {
        result.forEach { it.type = type }
    }
}