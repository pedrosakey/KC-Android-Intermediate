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
                1.0f,2.0f,"","","","")



        val shopEntityDao = ShopDAO(dbhelper)

        val id = shopEntityDao.insert(shop)

        assert(id > 0)

    }

    //TODO: convert into a valid test

    private fun test(){

        // NEVER DO THIS
        // HORROR !!!!!
        val dbhelper = build(appContext, "mydb.sqlite",1)

        val shopEntityDao = ShopDAO(dbhelper)

        val deletedAll = shopEntityDao.deleteAll()

        val shop = ShopEntity(1,1,"MyShop 1","desc 1",
                1.0f,2.0f,"","","","")

        val shop2 = ShopEntity(2,1,"MyShop 2","desc 2",
                1.0f,2.0f,"","","","")


        val id = shopEntityDao.insert(shop)
        val id2 = shopEntityDao.insert(shop2)

        shopEntityDao.query().forEach {
            Log.d("Shop", it.name)
        }

    }
}
