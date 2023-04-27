package com.alancamargo.hearthstone.cards.di

import com.alancamargo.hearthstone.cards.data.local.CardsLocalDataSource
import com.alancamargo.hearthstone.cards.data.local.CardsLocalDataSourceImpl
import com.alancamargo.hearthstone.cards.data.remote.CardsRemoteDataSource
import com.alancamargo.hearthstone.cards.data.remote.CardsRemoteDataSourceImpl
import com.alancamargo.hearthstone.cards.data.repository.CardsRepositoryImpl
import com.alancamargo.hearthstone.cards.domain.repository.CardsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class CardsModule {

    @Binds
    @ViewModelScoped
    abstract fun bindCardsRemoteDataSource(impl: CardsRemoteDataSourceImpl): CardsRemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindCardsLocalDataSource(impl: CardsLocalDataSourceImpl): CardsLocalDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindCardsRepository(impl: CardsRepositoryImpl): CardsRepository
}
