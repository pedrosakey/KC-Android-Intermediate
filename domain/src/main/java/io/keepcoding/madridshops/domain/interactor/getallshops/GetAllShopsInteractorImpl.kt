package io.keepcoding.madridshops.domain.interactor.getallshops

import android.content.Context
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.model.Shop
import io.keepcoding.madridshops.domain.model.Shops
import io.keepcoding.madridshops.repository.Repository
import io.keepcoding.madridshops.repository.RepositoryImpl
import io.keepcoding.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference

class GetAllShopsInteractorImpl (context: Context) : GetAllShopsInteractor {
        private val weakContext = WeakReference<Context>(context)
        private val repository: Repository = RepositoryImpl(weakContext.get() !!)

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        repository.getAllShops(success = {
            val shops: Shops = entityMapper(it)
            success.successCompletion(shops)
        }, error = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ShopEntity>): Shops {
        val tempList = ArrayList<Shop>()
        list.forEach {
            val shop = Shop(it.id.toInt(),it.name, it.address, saveSafeCoordinate(it.latitude),saveSafeCoordinate(it.longitude))
            tempList.add(shop)
        }

        val shops = Shops(tempList)
        return shops
    }
}

private fun saveSafeCoordinate( ramdomCoordinate : String) : String{
    // quitar espacios
    var a = ramdomCoordinate.trim()
    var b = a.substringBefore(',',a)
    return b

}