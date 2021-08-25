package com.faranjit.meditory.features.home.data

import com.faranjit.meditory.features.home.data.datasource.HomeLocalDataSource
import com.faranjit.meditory.features.home.data.datasource.HomeRemoteDataSource
import com.faranjit.meditory.features.home.domain.HomeRepository

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class HomeDataRepository(
    private val remoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource
) : HomeRepository {

    override suspend fun getHomeData() = remoteDataSource.getHomeData()

    override fun getUsername() = localDataSource.getUsername()
}