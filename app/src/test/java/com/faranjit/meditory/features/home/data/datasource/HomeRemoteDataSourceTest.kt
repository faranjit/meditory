package com.faranjit.meditory.features.home.data.datasource

import com.faranjit.meditory.BaseUnitTest
import com.faranjit.meditory.FakeExecutor
import com.faranjit.meditory.base.ResponseWrapper
import com.faranjit.meditory.features.home.data.HomeApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
@ExperimentalCoroutinesApi
class HomeRemoteDataSourceTest : BaseUnitTest() {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType())
        ).build()
        .create(HomeApi::class.java)

    private val remoteDataSource =
        HomeRemoteDataSource(api, FakeExecutor(mockWebServer, "home-response.json"))

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should getHomeData fetches home data successfuly`() {
        runBlocking {
            // Given

            // When
            val response = remoteDataSource.getHomeData()

            // Then
            assertTrue(response is ResponseWrapper.Success)
            assertNotNull(response.data)
            assertTrue(response.data.meditations.isNotEmpty())
        }
    }
}