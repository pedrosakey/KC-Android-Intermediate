package io.keepcoding.madridshops.repository.network

import io.keepcoding.madridshops.repository.ErrorCompletion
import io.keepcoding.madridshops.repository.SuccessCompletion

internal interface GetJsonManager {
    fun execute(url: String, success: SuccessCompletion<String>, error: ErrorCompletion)
}