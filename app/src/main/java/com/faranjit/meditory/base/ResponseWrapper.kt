package com.faranjit.meditory.base

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
sealed class ResponseWrapper<out T : BaseResponse> {

    /**
     * Basarili network response'larini sarmalar
     */
    data class Success<out T : BaseResponse>(val data: T) : ResponseWrapper<T>()

    /**
     * Hatali response'lari sarmalar
     */
    data class ServiceError(
        val httpCode: Int? = null,
        val errorMessage: String
    ) : ResponseWrapper<Nothing>()
}