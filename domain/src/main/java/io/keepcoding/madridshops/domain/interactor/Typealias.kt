package io.keepcoding.madridshops.domain.interactor

import io.keepcoding.madridshops.domain.model.Shops

typealias CodeClosure = () -> Unit
typealias SuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
