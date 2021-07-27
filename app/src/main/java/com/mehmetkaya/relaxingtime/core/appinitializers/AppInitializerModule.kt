package com.mehmetkaya.relaxingtime.core.appinitializers

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface AppInitializerModule {
    @Binds
    @IntoSet
    fun bindEpoxyInitializer(initializer: EpoxyInitializer): AppInitializer

    @Binds
    @IntoSet
    fun bindTimberInitializer(initializer: TimberInitializer): AppInitializer
}
