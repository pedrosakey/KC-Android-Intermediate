package io.keepcoding.madridshops

import android.content.Context
import android.support.multidex.MultiDexApplication
import android.util.Log
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllActivitiesInteractorImpl
import io.keepcoding.madridshops.domain.model.Shops


class MadridShopsApp : MultiDexApplication () {

    val context: Context = this

    override fun onCreate() {
        super.onCreate()

      // Descargamos al principio para trabajar desde cache
        GetAllShopsInteractorImpl(context).execute(

                success = object : SuccessCompletion<Shops> {
                    override fun successCompletion(shops: Shops) {

                        GetAllActivitiesInteractorImpl(context).execute(

                                success = object : SuccessCompletion<Shops> {
                                    override fun successCompletion(shops: Shops) {


                                    }
                                },
                                error = object : ErrorCompletion {
                                    override fun errorCompletion(errorMessage: String) {
                                        Log.d("error", errorMessage)
                                    }
                                })

                    }
                },
                error = object : ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("error", errorMessage)
                    }
                })


    }


    override fun onLowMemory() {
        super.onLowMemory()
    }

}