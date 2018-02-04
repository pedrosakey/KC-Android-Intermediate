package io.keepcoding.madridshops.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import io.keepcoding.madridshops.repository.db.build
import io.keepcoding.madridshops.repository.db.dao.ShopDAO
import io.keepcoding.madridshops.repository.model.ShopEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    @Throws(Exception::class)
    fun given_valid_shopentity_it_gets_inserted_correctly() {

        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        val shop = ShopEntity(1,1,"MyShop","",
                1.0f,2.0f,"","","","")

        val dbhelper = build(appContext, "mydb.sqlite",1)

        val shopEntityDao = ShopDAO(dbhelper)

        val id = shopEntityDao.insert(shop)

        assert(id > 0)

    }
}
