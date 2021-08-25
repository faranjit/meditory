package com.faranjit.meditory.features.home.data

import com.faranjit.meditory.BaseUnitTest
import com.faranjit.meditory.features.home.data.datasource.HomeRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class HomeDataRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var remoteDataSource: HomeRemoteDataSource

    @InjectMocks
    private lateinit var repository: HomeDataRepository

    @Test
    fun `should getHomeData be called`() {
        runBlocking {
            // Given

            // When
            repository.getHomeData()

            // Then
            verify(remoteDataSource).getHomeData()
        }
    }
}