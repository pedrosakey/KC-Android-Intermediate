package io.keepcoding.madridshops.domain.interactor.getallplaces

import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.model.ElementType
import io.keepcoding.madridshops.domain.model.Shops

interface GetAllPlacesInteractor  {
    fun execute(element: ElementType, success: SuccessCompletion<Shops>, error: ErrorCompletion)
}
