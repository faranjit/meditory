package com.faranjit.meditory.features.home.data.datasource

import com.faranjit.meditory.base.Executor
import com.faranjit.meditory.base.ResponseWrapper
import com.faranjit.meditory.features.home.data.HomeApi
import com.faranjit.meditory.features.home.data.response.HomeResponse

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class HomeRemoteDataSource(
    private val homeApi: HomeApi,
    private val executor: Executor
) {

    suspend fun getHomeData(): ResponseWrapper<HomeResponse> =
        executor.call {
            homeApi.getHomeData()
        }
}