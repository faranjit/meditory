package com.faranjit.meditory.features.home.domain

import com.faranjit.meditory.base.ResponseWrapper
import com.faranjit.meditory.features.home.data.response.HomeResponse

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
interface HomeRepository {

    /**
     * Anasayfada gosterilecek meditasyon ve story bilgilerini getirir.
     */
    suspend fun getHomeData(): ResponseWrapper<HomeResponse>

    /**
     * Login'de kaydedilen username'i getirir.
     */
    fun getUsername(): String?
}