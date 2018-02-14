package io.keepcoding.madridshops.repository

import android.content.Context
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import io.keepcoding.madridshops.repository.cache.Cache
import io.keepcoding.madridshops.repository.cache.CacheImpl
import io.keepcoding.madridshops.repository.model.ShopEntity
import io.keepcoding.madridshops.repository.model.ShopsResponseEntity
import io.keepcoding.madridshops.repository.network.GetJsonManager
import io.keepcoding.madridshops.repository.network.GetJsonManagerVolleyImpl
import io.keepcoding.madridshops.repository.network.json.JsonEntitiesParser
import io.keepcoding.repository.BuildConfig
import java.lang.ref.WeakReference

public class RepositoryImpl(context: Context) : Repository {

    private val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImpl(weakContext.get()!!)

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // read all Shops from cache
        cache.getAllShops(succes = {
        // if there's shops in cache

            success(it)

        }, error = {
        // if no shops in cache --> network
            populateCache(success, error)
        })

    }

    private fun populateCache(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // perform network request

        val jsonManager : GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL, success = object: SuccessCompletion<String>{
            override fun successCompletion(e: String) {
            val parser = JsonEntitiesParser()
            var responseEntity : ShopsResponseEntity
            try {
                responseEntity = parser.parse<ShopsResponseEntity>(e)
            }catch(e: InvalidFormatException) {
                error("ERROR PARSING")
                return
            }
            // store result in cache
                cache.saveAllShops(responseEntity.result, success = {
                    success(responseEntity.result)
                }, error = {
                    error("Something happene on the way to heaven")
                })

            }

        }, error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


        // store result in cache
    }

    override fun deleteAllShops(succes: () -> Unit, error: (errorMessage: String) -> Unit) {

        cache.deleteAllShops(succes, error)

    }

}
