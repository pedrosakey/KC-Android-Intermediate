package io.keepcoding.madridshops.domain.model

import io.keepcoding.repository.BuildConfig


data class ElementType(val type: Int, val url: String) {
    override fun equals(other: Any?): Boolean {
        var result = false
        if(other is ElementType){
            result = type === other.type

        }
        return  result
    }

    override fun hashCode(): Int {
        var result = type
        result = 31 * result + url.hashCode()
        return result
    }
}

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