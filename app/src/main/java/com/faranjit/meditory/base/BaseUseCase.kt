package com.faranjit.meditory.base

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
abstract class BaseUseCase<T, in Params> {

    abstract fun execute(params: Params): T
}