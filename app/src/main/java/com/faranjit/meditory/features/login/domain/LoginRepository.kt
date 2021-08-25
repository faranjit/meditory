package com.faranjit.meditory.features.login.domain

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
interface LoginRepository {

    /**
     * Username'i daha sonra kullanmak icin kaydeder
     */
    fun saveUsername(username: String)
}