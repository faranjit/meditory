package com.faranjit.meditory.features.home.domain

import com.faranjit.meditory.base.ResponseWrapper
import com.faranjit.meditory.features.home.data.response.HomeResponse

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
interface HomeDataSource {

    /**
     * Anasayfada gosterilecek bilgileri getirir.
     * Cevabi basarili/basarisiz durumlari icin sarmalar.
     */
    suspend fun getHomeData(): ResponseWrapper<HomeResponse>
}