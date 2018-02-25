package io.keepcoding.madridshops.repository.cache

import io.keepcoding.madridshops.repository.model.ElementType
import io.keepcoding.madridshops.repository.model.ShopEntity

internal interface Cache {
    fun getAllShops(succes: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(succes: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllActivities(succes: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    }