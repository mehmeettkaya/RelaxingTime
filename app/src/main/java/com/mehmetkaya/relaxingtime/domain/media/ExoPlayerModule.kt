package com.mehmetkaya.relaxingtime.domain.media

import android.content.Context
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSourceFactory
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExoPlayerModule {
    @Provides
    @Singleton
    fun provideMediaDataSourceFactory(
        @ApplicationContext context: Context
    ): DataSource.Factory {
        return DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, "mediaPlayer")
        )
    }

    @Provides
    @Singleton
    fun provideMediaSourceFactory(
        mediaDataSourceFactory: DataSource.Factory
    ): MediaSourceFactory {
        return DefaultMediaSourceFactory(mediaDataSourceFactory)
    }

    @Provides
    @Singleton
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        mediaSourceFactory: MediaSourceFactory
    ): SimpleExoPlayer {
        return SimpleExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()
    }
}
