package com.alancamargo.hearthstone.filters.di

import android.content.Context
import android.widget.Toast
import com.alancamargo.hearthstone.core.database.DatabaseProvider
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.core.network.ApiProvider
import com.alancamargo.hearthstone.filters.data.api.FiltersApi
import com.alancamargo.hearthstone.filters.data.db.FiltersDao
import com.alancamargo.hearthstone.filters.data.db.FiltersDatabase
import com.alancamargo.hearthstone.navigation.CardsActivityNavigation
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

    @Provides
    @Singleton
    fun provideFiltersDao(databaseProvider: DatabaseProvider): FiltersDao {
        val database = databaseProvider.provideDatabase(
            clazz = FiltersDatabase::class,
            databaseName = "filters"
        )

        return database.getFiltersDao()
    }

    @Provides
    @Singleton
    fun provideTemporaryCardsActivityNavigation(): CardsActivityNavigation {
        return object : CardsActivityNavigation {
            override fun startActivity(context: Context, filter: String, type: FilterType) {
                Toast.makeText(
                    context,
                    "Filter: $filter - Type: $type",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
