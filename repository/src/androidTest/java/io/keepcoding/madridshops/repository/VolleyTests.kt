package io.keepcoding.madridshops.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import io.keepcoding.madridshops.repository.db.build
import io.keepcoding.madridshops.repository.db.dao.ShopDAO
import io.keepcoding.madridshops.repository.model.ShopEntity
import io.keepcoding.madridshops.repository.network.GetJsonManager
import io.keepcoding.madridshops.repository.network.GetJsonManagerVolleyImpl

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class VolleyTests {

        val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    @Throws(Exception::class)
    fun given_valid_url_we_get_non_null_json_as_string () {
        val url = "http://madrid-shops.com/json_new/getShops.php"

        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(appContext)

        jsonManager.execute(url, success = object: SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                assertTrue(e.isNotEmpty())
            }

        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                assertTrue(false)
            }

        })

    }
}
