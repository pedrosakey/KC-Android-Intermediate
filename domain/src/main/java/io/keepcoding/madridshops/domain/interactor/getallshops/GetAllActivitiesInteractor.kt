package io.keepcoding.madridshops.domain.interactor.getallshops

import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.model.Shops

interface GetAllActivitiesInteractor {
    fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion)
}