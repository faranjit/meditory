package com.faranjit.meditory.features.home.domain

import com.faranjit.meditory.base.BaseRequestUseCase
import com.faranjit.meditory.base.ResponseWrapper
import com.faranjit.meditory.features.home.data.response.HomeResponse

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class GetHomeData(
    private val homeRepository: HomeRepository
) : BaseRequestUseCase<HomeResponse, Unit>() {

    override suspend fun buildUseCase(params: Unit?): ResponseWrapper<HomeResponse> =
        homeRepository.getHomeData()
}