package com.alancamargo.hearthstone.cards.di

import com.alancamargo.hearthstone.cards.ui.navigation.CardListActivityNavigationImpl
import com.alancamargo.hearthstone.navigation.CardListActivityNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
internal abstract class CardsNavigationModule {

    @Binds
    @ActivityScoped
    abstract fun bindCardListActivityNavigation(
        impl: CardListActivityNavigationImpl
    ): CardListActivityNavigation
}
