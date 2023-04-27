package com.alancamargo.hearthstone.cards.di

import com.alancamargo.hearthstone.cards.data.api.CardsApi
import com.alancamargo.hearthstone.core.network.ApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CardsDataModule {

    @Provides
    @Singleton
    fun provideCardsApi(apiProvider: ApiProvider): CardsApi {
        return apiProvider.provideService(CardsApi::class.java)
    }
}
