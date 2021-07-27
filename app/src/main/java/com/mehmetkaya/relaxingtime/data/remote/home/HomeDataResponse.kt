package com.mehmetkaya.relaxingtime.data.remote.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeData(
    @SerialName("isBannerEnabled") val isBannerEnabled: Boolean,
    @SerialName("meditations") val meditations: List<Meditation>,
    @SerialName("stories") val stories: List<Story>
)

@Serializable
data class Meditation(
    @SerialName("title") val title: String,
    @SerialName("subtitle") val subtitle: String,
    @SerialName("image") val image: Image,
    @SerialName("releaseDate") val releaseDate: Long,
    @SerialName("content") val content: String
)

@Serializable
data class Story(
    @SerialName("name") val name: String,
    @SerialName("category") val category: String,
    @SerialName("image") val image: Image,
    @SerialName("date") val date: Long,
    @SerialName("text") val text: String
)

@Serializable
data class Image(
    @SerialName("small") val small: String,
    @SerialName("large") val large: String
)
