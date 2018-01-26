package io.keepcoding.madridshops.domain.interactor.internetstatus

import io.keepcoding.madridshops.domain.interactor.CodeClosure
import io.keepcoding.madridshops.domain.interactor.ErrorClosure

class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}