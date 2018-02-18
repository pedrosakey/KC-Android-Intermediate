package io.keepcoding.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractorFakeImpl
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.interactor.deleteallshops.DeleteAllShopsImpl
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import io.keepcoding.madridshops.domain.model.Shops


class MadridShopsApp : MultiDexApplication () {
    override fun onCreate() {
        super.onCreate()

        //init code application wide
        Log.d("run", "Arrancamos aplicación MultiDex")

        val allShopsInteractor = GetAllShopsInteractorImpl(this)
        allShopsInteractor.execute(
                success = object : SuccessCompletion<Shops> {
                    override fun successCompletion(shops: Shops) {
                        Log.d("success", "Count: " + shops.count())
                    }
                },
                error = object : ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("error", errorMessage)
                    }
                })
    }
    /*
        DeleteAllShopsImpl(this).execute(success = {
            Log.d("success", "success deleteAllShops")
        }, error = {
            Log.d("error", "success")

        })
    }
    */

    override fun onLowMemory() {
        super.onLowMemory()
    }

}