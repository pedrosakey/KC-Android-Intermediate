package io.keepcoding.madridshops.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import io.keepcoding.madridshops.repository.db.build
import io.keepcoding.madridshops.repository.db.dao.ShopDAO
import io.keepcoding.madridshops.repository.model.ShopEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class ShopDAOTests {

    // Context of the app under test.
    val appContext = InstrumentationRegistry.getTargetContext()
    val dbhelper = build(appContext, "mydb.sqlite",1)
    
    @Test
    @Throws(Exception::class)
    fun given_valid_shopentity_it_gets_inserted_correctly() {


        val shop = ShopEntity(1,1,"MyShop","",
                "1","2","","","","")



        val shopEntityDao = ShopDAO(dbhelper)

        val id = shopEntityDao.insert(shop)

        assert(id > 0)

    }

    @Test
    @Throws(Exception::class)
    private fun is_ok_create_empty_db_helper_and_insert_two_shops(){

        val dbhelper = build(appContext, "mydb.sqlite",1)
        val shopEntityDao = ShopDAO(dbhelper)

        shopEntityDao.deleteAll()

        val shop = ShopEntity(1,1,"MyShop 1","desc 1",
                "","","","","","")

        val shop2 = ShopEntity(2,1,"MyShop 2","desc 2",
                "","","","","","")


         shopEntityDao.insert(shop)
         shopEntityDao.insert(shop2)

        val shops = shopEntityDao.query()
        assertEquals(shops[0].name, shop.name)
        assertEquals(shops[0].name, shop.name)

    }
}
