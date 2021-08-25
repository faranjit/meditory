package com.faranjit.meditory.features.login.data

import com.faranjit.meditory.features.login.domain.LoginRepository

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class LoginDataRepository(
    private val localDataSource: LoginLocalDataSource
) : LoginRepository {

    override fun saveUsername(username: String) = localDataSource.saveUsername(username)
}