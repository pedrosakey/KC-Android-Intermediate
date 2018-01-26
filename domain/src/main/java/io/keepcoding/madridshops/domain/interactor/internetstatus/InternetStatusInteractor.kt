package io.keepcoding.madridshops.domain.interactor.internetstatus

import io.keepcoding.madridshops.domain.interactor.CodeClosure
import io.keepcoding.madridshops.domain.interactor.ErrorClosure


interface InternetStatusInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}
