package com.faranjit.meditory.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class RequestExecutor : Executor {

    override suspend fun <Response : BaseResponse> call(block: suspend () -> Response): ResponseWrapper<Response> =
        withContext(
            Dispatchers.IO
        ) {
            try {
                ResponseWrapper.Success(block())
            } catch (t: Throwable) {
                when (t) {
                    is HttpException -> {
                        ResponseWrapper.ServiceError(
                            httpCode = t.code(),
                            errorMessage = t.message()
                        )
                    }
                    else -> ResponseWrapper.ServiceError(
                        errorMessage = t.message ?: "An error occurred"
                    )
                }
            }
        }
}