package com.faranjit.meditory.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.meditory.base.BaseViewModel
import com.faranjit.meditory.features.home.domain.GetHomeData
import com.faranjit.meditory.features.home.presentation.model.MeditationModel

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class HomeViewModel(
    private val getHomeData: GetHomeData
) : BaseViewModel() {

    private val meditations = MutableLiveData<List<MeditationModel>>()
    val meditationsLiveData: LiveData<List<MeditationModel>>
        get() = meditations

    fun getHomeData() {
        runAsync {
            val homeResponse = getHomeData.execute(Unit)
            meditations.value = homeResponse.meditations.map {
                it.toMeditationModel()
            }
        }
    }
}