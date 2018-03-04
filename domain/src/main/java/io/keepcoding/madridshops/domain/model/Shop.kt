package io.keepcoding.madridshops.domain.model

import java.util.*
import java.io.Serializable


data class Shop(val id: Int,
                val name: String,
                val logo: String,
                val address: String,
                val latitude: String,
                val longitude: String,
                val timeTable: String,
                val description: String
): Serializable {

    init {
        Shops(ArrayList<Shop>())
    }

    override fun toString(): String {
        return name
    }

}


class Shops(val shops: MutableList<Shop>) : Aggregate<Shop> , Serializable{
    override fun count(): Int {
        return shops.size
    }

    override fun all(): List<Shop> {
        return shops
    }

    override fun get(position: Int): Shop {
        return shops.get(position)
    }

    override fun add(element: Shop) {
        shops.add(element)
    }

    override fun delete(position: Int) {
        shops.removeAt(position)
    }

    override fun delete(element: Shop) {
        shops.remove(element)
    }


}