package io.keepcoding.madridshops.repository

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import org.junit.Test

import io.keepcoding.madridshops.repository.model.ShopEntity
import io.keepcoding.madridshops.repository.model.ShopsResponseEntity
import io.keepcoding.madridshops.repository.network.json.JsonEntitiesParser
import io.keepcoding.madridshops.util.ReadJsonFile
import org.junit.Assert.*

class JSONParsingTests {
    @Test
    @Throws(Exception::class)
    fun given_invalid_string_containing_json_with_wrong_latitude_it_parses_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shopWrongLatitude.json")
        assertTrue(false == shopsJson.isEmpty())

        // parsing

        val parser = JsonEntitiesParser()
        var shop : ShopEntity
        try {
            shop = parser.parse<ShopEntity>(shopsJson)
        } catch (e:InvalidFormatException) {
            shop = ShopEntity(1,1,"Parsing failed","",10f,10f,"","","","")
        }
        assertNotNull(shop)
        assertEquals("Parsing failed", shop.name)
        assertEquals(10f, shop.latitude,0.1f)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shop.json")
        assertTrue(false == shopsJson.isEmpty())

        // parsing

        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShopEntity>(shopsJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)
        assertEquals(40.4180563f, shop.latitude,0.0f)
    }

 @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_shops_it_parses_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shops.json")
        assertTrue(false == shopsJson.isEmpty())

        // parsing

        val parser = JsonEntitiesParser()
        val responseEntity = parser.parse<ShopsResponseEntity>(shopsJson)

        assertNotNull(responseEntity)
        assertEquals(6, responseEntity.result.count())
        assertEquals("Cortefiel - Preciados", responseEntity.result[0].name)
        assertEquals(40.4180563f, responseEntity.result[0].latitude,0.0f)
    }



}