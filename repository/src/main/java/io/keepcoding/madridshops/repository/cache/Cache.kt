package io.keepcoding.madridshops.repository.cache

import io.keepcoding.madridshops.repository.model.ElementTypeEntity
import io.keepcoding.madridshops.repository.model.ShopEntity

internal interface Cache {
    fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(succes: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllPlaces(element: ElementTypeEntity, succes: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)

    }