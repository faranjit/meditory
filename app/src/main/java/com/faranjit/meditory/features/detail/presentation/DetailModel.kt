package com.faranjit.meditory.features.detail.presentation

import android.os.Parcelable
import com.faranjit.meditory.features.home.presentation.model.MeditationModel
import com.faranjit.meditory.features.home.presentation.model.StoryModel
import kotlinx.parcelize.Parcelize

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
@Parcelize
data class DetailModel(
    val title: String,
    val content: String,
    val backgroundUrl: String,
    val date: String,
    val type: DetailType
) : Parcelable

/**
 * StoryModel'i DetailModel'e cevirir.
 */
fun StoryModel.toDetailModel() = DetailModel(
    name, text, image.large, date, DetailType.STORY
)

/**
 * MeditationModel'i DetailModel'e cevirir.
 */
fun MeditationModel.toDetailModel() = DetailModel(
    title, content, image.large, releaseDate, DetailType.MEDITATION
)