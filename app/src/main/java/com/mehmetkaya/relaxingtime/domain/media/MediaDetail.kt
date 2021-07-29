package com.mehmetkaya.relaxingtime.domain.media

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaDetail(
    val title: String,
    val content: String,
    val image: String,
    val date: Long
) : Parcelable
