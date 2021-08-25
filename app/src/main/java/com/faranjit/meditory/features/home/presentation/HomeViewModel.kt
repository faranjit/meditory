package com.faranjit.meditory.features.home.presentation

import com.faranjit.meditory.base.BaseViewModel
import com.faranjit.meditory.features.home.domain.GetHomeData

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class HomeViewModel(
    private val getHomeData: GetHomeData
) : BaseViewModel() {

    fun getHomeData() {
        runAsync {
            getHomeData.execute(Unit)
        }
    }
}