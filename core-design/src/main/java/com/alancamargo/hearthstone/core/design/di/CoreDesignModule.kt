package com.alancamargo.hearthstone.core.design.di

import android.content.Context
import com.alancamargo.hearthstone.core.design.tools.DialogueHelper
import com.alancamargo.hearthstone.core.design.tools.DialogueHelperImpl
import com.alancamargo.hearthstone.core.design.tools.ToastHelper
import com.alancamargo.hearthstone.core.design.tools.ToastHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
internal object CoreDesignModule {

    @Provides
    @ActivityScoped
    fun provideToastHelper(
        @ApplicationContext context: Context
    ): ToastHelper = ToastHelperImpl(context)

    @Provides
    @ActivityScoped
    fun provideDialogueHelper(): DialogueHelper = DialogueHelperImpl()
}
