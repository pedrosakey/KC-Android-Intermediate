package io.keepcoding.madridshops.domain.interactor.deleteallshops

import android.content.Context
import io.keepcoding.madridshops.domain.interactor.CodeClosure
import io.keepcoding.madridshops.domain.interactor.ErrorClosure
import io.keepcoding.madridshops.repository.RepositoryImpl
import java.lang.ref.WeakReference

class DeleteAllShopsImpl (context: Context) : DeleteAllShops {

    val weakContext = WeakReference<Context> (context)

    override fun execute(success: CodeClosure, error: ErrorClosure) {
        val repository = RepositoryImpl(weakContext.get() !!)

        repository.deleteAllShops(success, error)
    }
}