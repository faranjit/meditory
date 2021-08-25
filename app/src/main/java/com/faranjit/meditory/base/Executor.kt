package com.faranjit.meditory.base

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
interface Executor {

    /**
     *Tum network isteklerinin gecmesi gerektigi metod.
     * Islem basarili biterdse {@see ResponseWrapper.Success} doner.
     * Bir hata alinirsa {@see ResponseWrapper.ServiceError} doner
     */
    suspend fun <Response : BaseResponse> call(block: suspend () -> Response): ResponseWrapper<Response>
}