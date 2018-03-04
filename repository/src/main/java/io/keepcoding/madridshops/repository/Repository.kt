package io.keepcoding.madridshops.repository

import io.keepcoding.madridshops.repository.model.ElementTypeEntity
import io.keepcoding.madridshops.repository.model.ShopEntity

interface Repository {

    fun deleteAllShops(succes: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllPlaces(element: ElementTypeEntity, success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)

    }