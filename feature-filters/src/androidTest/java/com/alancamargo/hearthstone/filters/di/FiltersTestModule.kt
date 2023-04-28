package com.alancamargo.hearthstone.filters.di

import com.alancamargo.hearthstone.core.design.di.CoreDesignModule
import com.alancamargo.hearthstone.core.design.tools.DialogueHelper
import com.alancamargo.hearthstone.core.design.tools.ToastHelper
import com.alancamargo.hearthstone.core.di.BaseUrl
import com.alancamargo.hearthstone.core.di.BaseUrlModule
import com.alancamargo.hearthstone.core.test.web.mockWebServer
import com.alancamargo.hearthstone.navigation.CardListActivityNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [
        CoreDesignModule::class,
        BaseUrlModule::class
    ]
)
object FiltersTestModule {

    @Provides
    @Singleton
    fun provideMockToastHelper(): ToastHelper = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockDialogueHelper(): DialogueHelper = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideMockCardListActivityNavigation(): CardListActivityNavigation = mockk(relaxed = true)

    @Provides
    @Singleton
    @BaseUrl
    fun provideMockBaseUrl(): String = runBlocking(Dispatchers.IO) {
        mockWebServer.url("/").toString()
    }
}
