package com.faranjit.meditory.features.home.data

import com.faranjit.meditory.features.home.data.response.HomeResponse
import retrofit2.http.GET

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
interface HomeApi {

    /**
     * Anasayfada gosterilecek bilgileri getirir.
     */
    @GET("files/MobileInterviewProjectData.json")
    suspend fun getHomeData(): HomeResponse
}