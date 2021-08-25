package com.faranjit.meditory.features.home.data.response

import com.faranjit.meditory.base.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
@Serializable
class HomeResponse(
    @SerialName("isBannerEnabled")
    val isBannerEnabled: Boolean,
    @SerialName("meditations")
    val meditations: List<Meditation>,
    @SerialName("stories")
    val stories: List<Story>
) : BaseResponse()

@Serializable
class Meditation(
    @SerialName("title")
    val title: String,
    @SerialName("subtitle")
    val subtitle: String,
    @SerialName("image")
    val image: Image,
    @SerialName("releaseDate")
    val releaseDate: String,
    @SerialName("content")
    val content: String
)

@Serializable
class Story(
    @SerialName("name")
    val name: String,
    @SerialName("category")
    val category: String,
    @SerialName("image")
    val image: Image,
    @SerialName("date")
    val date: String,
    @SerialName("text")
    val text: String
)

@Serializable
class Image(
    @SerialName("small")
    val small: String,
    @SerialName("large")
    val large: String
)