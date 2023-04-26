package com.alancamargo.hearthstone.filters.di

import com.alancamargo.hearthstone.filters.data.local.FiltersLocalDataSource
import com.alancamargo.hearthstone.filters.data.local.FiltersLocalDataSourceImpl
import com.alancamargo.hearthstone.filters.data.remote.FiltersRemoteDataSource
import com.alancamargo.hearthstone.filters.data.remote.FiltersRemoteDataSourceImpl
import com.alancamargo.hearthstone.filters.data.repository.FiltersRepositoryImpl
import com.alancamargo.hearthstone.filters.domain.repository.FiltersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class FiltersModule {

    @Binds
    @ViewModelScoped
    abstract fun bindFiltersRemoteDataSource(
        impl: FiltersRemoteDataSourceImpl
    ): FiltersRemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindFiltersLocalDataSource(
        impl: FiltersLocalDataSourceImpl
    ): FiltersLocalDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindFiltersRepository(impl: FiltersRepositoryImpl): FiltersRepository
}
