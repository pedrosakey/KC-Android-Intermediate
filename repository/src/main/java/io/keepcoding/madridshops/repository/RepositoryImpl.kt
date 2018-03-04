package io.keepcoding.madridshops.repository

import android.content.Context
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import io.keepcoding.madridshops.repository.cache.Cache
import io.keepcoding.madridshops.repository.cache.CacheImpl
import io.keepcoding.madridshops.repository.model.ElementTypeEntity
import io.keepcoding.madridshops.repository.model.ElementTypeInitilizersEntity
import io.keepcoding.madridshops.repository.model.ShopEntity
import io.keepcoding.madridshops.repository.model.ShopsResponseEntity
import io.keepcoding.madridshops.repository.network.GetJsonManager
import io.keepcoding.madridshops.repository.network.GetJsonManagerVolleyImpl
import io.keepcoding.madridshops.repository.network.json.JsonEntitiesParser
import java.lang.ref.WeakReference

class RepositoryImpl(context: Context) : Repository {

    private val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImpl(weakContext.get()!!)

    override fun getAllPlaces( element: ElementTypeEntity, success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {

        cache.getAllPlaces(
                element,
                succes = {
            // If there's shops in cache
            success(it)
             }, error = {
            // if no shops in cache --> network
            populateCache(element, success, error)
        })

    }

    private fun populateCache(element: ElementTypeEntity, success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // Perform network request

        val jsonManager : GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(element.url, success = object: SuccessCompletion<String>{
            override fun successCompletion(e: String) {
            val parser = JsonEntitiesParser()
                val responseEntity : ShopsResponseEntity
                try {
                    responseEntity = parser.parse<ShopsResponseEntity>(e)
                    responseEntity.insertType(element.type)
                }catch(e: InvalidFormatException) {
                    error("ERROR PARSING")
                    return
                }
                // store result in cache
                cache.saveAllShops(responseEntity.result, success = {
                    success(responseEntity.result)
                }, error = {
                    error("Something happened")
                })

            }

        }, error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }

        })

    }

    override fun deleteAllShops(succes: () -> Unit, error: (errorMessage: String) -> Unit) {

        cache.deleteAllShops(succes, error)

    }

}
