package io.keepcoding.madridshops.repository.model

import io.keepcoding.repository.BuildConfig


data class ElementType (val type: Int, val url: String)

class ElementTypeInitilizers() {

    enum class TYPE(val index: Int) {
        SHOPS(1),
        ACTIVITIES(2)
        // Here we can add more elements...

    }

    val SHOPS = ElementType(TYPE.SHOPS.index, BuildConfig.MADRID_SHOPS_SERVER_URL)
    val ACTIVITIES = ElementType(TYPE.ACTIVITIES.index, BuildConfig.MADRID_ACTIVITIES_SERVER_URL)
    // Here we ca add more elements...
}