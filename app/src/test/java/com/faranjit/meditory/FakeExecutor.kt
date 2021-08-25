package com.faranjit.meditory

import com.faranjit.meditory.base.BaseResponse
import com.faranjit.meditory.base.Executor
import com.faranjit.meditory.base.ResponseWrapper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class FakeExecutor(
    private val mockWebServer: MockWebServer,
    private val mockResponseFile: String
) : Executor {

    override suspend fun <Response : BaseResponse> call(block: suspend () -> Response): ResponseWrapper<Response> {
        mockWebServer.enqueueResponse(mockResponseFile, 200)
        return ResponseWrapper.Success(block.invoke())
    }
}

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream("mock-responses/$fileName")

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}