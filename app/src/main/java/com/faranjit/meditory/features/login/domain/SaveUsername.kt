package com.faranjit.meditory.features.login.domain

import com.faranjit.meditory.base.BaseUseCase

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class SaveUsername(
    private val loginRepository: LoginRepository
) : BaseUseCase<Unit, SaveUsername.Params>() {

    override fun execute(params: Params) = loginRepository.saveUsername(params.username)

    data class Params(val username: String)
}