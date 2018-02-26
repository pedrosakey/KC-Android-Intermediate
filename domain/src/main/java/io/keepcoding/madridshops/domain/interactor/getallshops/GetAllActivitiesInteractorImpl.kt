package io.keepcoding.madridshops.domain.interactor.getallshops

import android.content.Context
import android.util.Log
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.model.Shop
import io.keepcoding.madridshops.domain.model.Shops
import io.keepcoding.madridshops.repository.Repository
import io.keepcoding.madridshops.repository.RepositoryImpl
import io.keepcoding.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference

class GetAllActivitiesInteractorImpl(context: Context) : GetAllShopsInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get() !!)

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {

        repository.getAllActivities(success = {
            val shops: Shops = entityMapper(it)
            success.successCompletion(shops)
        }, error = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ShopEntity>): Shops {
        val tempList = ArrayList<Shop>()
        list.forEach {
            val shop = Shop(it.id.toInt(),
                    it.name,
                    it.address,
                    it.latitude,
                    it.longitude,
                    it.openingHours,
                    it.description)
            tempList.add(shop)
        }

        val shops = Shops(tempList)
        return shops
    }
}