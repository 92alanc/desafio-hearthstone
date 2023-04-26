package com.alancamargo.hearthstone.filters.di

import com.alancamargo.hearthstone.core.network.ApiProvider
import com.alancamargo.hearthstone.filters.data.api.FiltersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FiltersDataModule {

    @Provides
    @Singleton
    fun provideFiltersApi(
        apiProvider: ApiProvider
    ): FiltersApi = apiProvider.provideService(FiltersApi::class.java)
}
