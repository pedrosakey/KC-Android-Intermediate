package io.keepcoding.madridshops.repository

import io.keepcoding.madridshops.repository.model.ShopEntity

interface Repository {

    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(succes: () -> Unit, error: (errorMessage: String) -> Unit)
}