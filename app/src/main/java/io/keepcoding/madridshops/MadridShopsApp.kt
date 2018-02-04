package io.keepcoding.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractorFakeImpl
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.model.Shops
import io.keepcoding.madridshops.repository.db.build
import io.keepcoding.madridshops.repository.db.dao.ShopDAO
import io.keepcoding.madridshops.repository.model.ShopEntity

typealias SuccessClosure = (shops: Shops) -> Unit

class MadridShopsApp : MultiDexApplication () {
    override fun onCreate() {
        super.onCreate()

        //init code application wide

        Log.d("Appio","onCreate")

        val allShopsInteractor = GetAllShopsInteractorFakeImpl()
        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops> {
                    override fun successCompletion(shops: Shops) {
                        Log.d("Shops","Count: " + shops.count())
                }
        },
                error = object : ErrorCompletion {
                     override fun errorCompletion(errorMessage: String) {
                    }

                }
        )

        allShopsInteractor.execute(success = { shops: Shops ->

        }, error = {msg: String -> Unit
        })
        test()
    }

    private fun test(){

        // NEVER DO THIS
        // HORROR !!!!!
        val dbhelper = build(this, "mydb.sqlite",1)

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

    override fun onLowMemory() {
        super.onLowMemory()
    }

}