package io.keepcoding.madridshops.domain.interactor.getallshops

import io.keepcoding.madridshops.domain.interactor.ErrorClosure
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessClosure
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.model.Shop
import io.keepcoding.madridshops.domain.model.Shops



class GetAllShopsInteractorFakeImpl: GetAllShopsInteractor {
    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        var allOk = false

        // connect to the repository
        if(allOk) {
            val shops = createFakeListOfShops()
            success.successCompletion(shops)
        } else {
            error.errorCompletion("Error while accesing the Repository")
        }
    }

    fun execute(success: SuccessClosure, error: ErrorClosure) {
        var allOk = false

        // connect to the repository
        if(allOk) {
            val shops = createFakeListOfShops()
            success(shops)
        } else {
            error("Error while accesing the Repository")
        }
    }

    fun createFakeListOfShops(): Shops {
        val list = ArrayList<Shop>()

        for(i in 0..100) {
            val shop = Shop(i, "Shop " + i, "Address " + i,"40", "-3")
            list.add(shop)
        }

        val shops = Shops(list)
        return shops
    }

}
