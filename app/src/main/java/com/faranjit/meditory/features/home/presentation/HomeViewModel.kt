package com.faranjit.meditory.features.home.presentation

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.meditory.base.BaseViewModel
import com.faranjit.meditory.features.home.domain.GetHomeData
import com.faranjit.meditory.features.home.domain.GetUsername
import com.faranjit.meditory.features.home.presentation.model.MeditationModel
import com.faranjit.meditory.features.home.presentation.model.StoryModel

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class HomeViewModel(
    private val getHomeData: GetHomeData,
    private val getUsername: GetUsername
) : BaseViewModel() {

    val bannerVisible = ObservableBoolean()
    val bannerText = ObservableField<String>()

    private val meditations = MutableLiveData<List<MeditationModel>>()
    val meditationsLiveData: LiveData<List<MeditationModel>>
        get() = meditations

    private val stories = MutableLiveData<List<StoryModel>>()
    val storiesLiveData: LiveData<List<StoryModel>>
        get() = stories

    /**
     * Kaydedilmis username'i local storage'dan getirir ve formatlar.
     */
    fun formatUsername(template: String) {
        val username = getUsername.execute(Unit)
        bannerText.set(template.format(username))
    }

    /**
     * Anasayfada listelecen datalari almak icin istek baslatir
     * ve donen response'u parse ederek ui'i gunceller.
     */
    fun getHomeData() {
        runAsync {
            val homeResponse = getHomeData.execute(Unit)

            bannerVisible.set(homeResponse.isBannerEnabled)

            meditations.value = homeResponse.meditations.map {
                it.toMeditationModel()
            }

            stories.value = homeResponse.stories.map {
                it.toStoryModel()
            }
        }
    }
}