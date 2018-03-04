package io.keepcoding.madridshops.repository.cache

import android.content.Context
import io.keepcoding.madridshops.repository.db.DBHelper
import io.keepcoding.madridshops.repository.db.build
import io.keepcoding.madridshops.repository.db.dao.ShopDAO
import io.keepcoding.madridshops.repository.model.ElementTypeEntity
import io.keepcoding.madridshops.repository.model.ElementTypeInitilizersEntity
import io.keepcoding.madridshops.repository.model.ShopEntity
import io.keepcoding.madridshops.repository.thread.DispatchOnMainTread
import java.lang.ref.WeakReference

 internal class CacheImpl (context: Context): Cache {

     val weakContext = WeakReference<Context>(context)
     var dbName = "MadridShops.sqlite"
     var dbVersion = 1

     override fun deleteAllShops(succes: () -> Unit, error: (errorMessage: String) -> Unit) {
         Thread(Runnable {
             val succesDeleting = ShopDAO(cacheDBHelper()).deleteAll()

             DispatchOnMainTread(Runnable {
                 if (succesDeleting) {
                     succes()
                 } else {
                     error("Error deleting")
                 }
             })

         }).run()


     }
//TODO: Sacar a una clausura el thread
    override fun getAllPlaces(element: ElementTypeEntity, succes: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
    Thread(Runnable {
        val shops = ShopDAO(cacheDBHelper()).query(element.type)
        DispatchOnMainTread(Runnable {
            if (shops.count() > 0) {
                succes(shops)
            } else {
                error("No shops")
            }
        })

    }).run()
}


     override fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
         Thread(Runnable {
             try {
                 shops.forEach { ShopDAO(cacheDBHelper()).insert(it.fixCoordinates()) }
             } catch (e: Exception) {
                 DispatchOnMainTread(Runnable {
                     error("Error inserting shops")
                 })
             }
         }).run()
     }


     fun cacheDBHelper(): DBHelper {
         return build(weakContext.get()!!, dbName, dbVersion)
     }

}
