package com.mehmetkaya.relaxingtime.domain.media

import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.mehmetkaya.core.domain.UseCase
import javax.inject.Inject

class CreateMediaSource @Inject constructor(
    private val mediaDataSourceFactory: DataSource.Factory
) : UseCase<String, ProgressiveMediaSource> {

    override suspend fun invoke(input: String): ProgressiveMediaSource {
        val mediaItem = MediaItem.fromUri(input)

        return ProgressiveMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(mediaItem)
    }
}
