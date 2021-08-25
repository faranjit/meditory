package com.faranjit.meditory.features.home.domain

import com.faranjit.meditory.base.BaseUseCase

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class GetUsername(
    private val homeRepository: HomeRepository
) : BaseUseCase<String?, Unit>() {

    override fun execute(params: Unit) = homeRepository.getUsername()
}