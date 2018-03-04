package io.keepcoding.madridshops

import android.content.Context
import android.support.multidex.MultiDexApplication
import android.util.Log
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.interactor.getallplaces.GetAllPlacesInteractorImpl
import io.keepcoding.madridshops.domain.model.ElementTypeInitilizers

import io.keepcoding.madridshops.domain.model.Shops


class MadridShopsApp : MultiDexApplication () {

    val context: Context = this

    override fun onCreate() {
        super.onCreate()

      // Descargamos al principio para trabajar desde cache

        GetAllPlacesInteractorImpl(context).execute(

                ElementTypeInitilizers().SHOPS,

                success = object : SuccessCompletion<Shops> {
                    override fun successCompletion(e: Shops) {

                        GetAllPlacesInteractorImpl(context).execute(

                                ElementTypeInitilizers().ACTIVITIES,

                                success = object : SuccessCompletion<Shops> {
                                    override fun successCompletion(e: Shops) {

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