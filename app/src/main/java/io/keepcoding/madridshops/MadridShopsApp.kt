package io.keepcoding.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractorFakeImpl
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.model.Shops

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
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

}