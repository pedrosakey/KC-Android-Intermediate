package io.keepcoding.madridshops.domain.interactor.deleteallshops

import io.keepcoding.madridshops.domain.interactor.CodeClosure
import io.keepcoding.madridshops.domain.interactor.ErrorClosure

interface DeleteAllShops {
    fun execute(success: CodeClosure, error: ErrorClosure)
}
