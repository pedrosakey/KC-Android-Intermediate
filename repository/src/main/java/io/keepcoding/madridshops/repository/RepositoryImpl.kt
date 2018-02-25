package io.keepcoding.madridshops.repository

import android.content.Context
import android.util.Log
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import io.keepcoding.madridshops.repository.cache.Cache
import io.keepcoding.madridshops.repository.cache.CacheImpl
import io.keepcoding.madridshops.repository.model.ElementType
import io.keepcoding.madridshops.repository.model.ElementTypeInitilizers
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

    override fun getAllActivities(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        cache.getAllActivities(succes = {
            success(it)
        }, error = {
            populateCache(ElementTypeInitilizers().ACTIVITIES, success, error)
        })


    }


    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Log.d("getshops", "Repository  - RepositoryImpl getAllShops ")

        // read all Shops from cache
        cache.getAllShops(succes = {
        // if there's shops in cache
            Log.d("getshops", "Repository  - RepositoryImpl si ok la cogemos de chache ")

            success(it)

        }, error = {
        // if no shops in cache --> network
            Log.d("getshops", "Repository  - RepositoryImpl No existen en chache vamos a red ")
            populateCache(ElementTypeInitilizers().SHOPS, success, error)
        })

    }

    private fun populateCache(element: ElementType, success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // Perform network request

        val jsonManager : GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(element.url, success = object: SuccessCompletion<String>{
            override fun successCompletion(e: String) {
            Log.d("getshops", "Repository  - RepositoryImpl populateCache tenemos el json ")
            val parser = JsonEntitiesParser()
                var responseEntity : ShopsResponseEntity
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
                    error("Something happened on the way to heaven")
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
