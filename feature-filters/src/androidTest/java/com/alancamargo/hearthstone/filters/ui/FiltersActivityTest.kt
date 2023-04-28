package com.alancamargo.hearthstone.filters.ui

import com.alancamargo.hearthstone.core.design.tools.DialogueHelper
import com.alancamargo.hearthstone.core.design.tools.ToastHelper
import com.alancamargo.hearthstone.filters.ui.robots.given
import com.alancamargo.hearthstone.navigation.CardListActivityNavigation
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
internal class FiltersActivityTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var mockToastHelper: ToastHelper

    @Inject
    lateinit var mockDialogueHelper: DialogueHelper

    @Inject
    lateinit var mockCardListActivityNavigation: CardListActivityNavigation

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun whenLoading_shouldShowShimmer() {
        given {
            launchWithDelayedWebResponse()
        } then {
            showShimmer()
        }
    }

    @Test
    fun withSuccess_shouldShowCorrectNumberOfFilterBlocks() {
        given {
            launchWithSuccess()
        } then {
            filterBlockCountIs(count = 5)
        }
    }

    @Test
    fun withNetworkError_shouldShowNetworkErrorMessage() {
        given {
            launchDisconnected()
        } then {
            showNetworkError()
        }
    }

    @Test
    fun withGenericError_shouldShowGenericErrorMessage() {
        given {
            launchWithGenericError()
        } then {
            showGenericError()
        }
    }

    @Test
    fun whenClickShowAppInfo_shouldShowAppInfo() {
        given {
            launchWithSuccess()
        } withAction {
            clickAppInfo()
        } then {
            showAppInfo()
        }
    }

    @Test
    fun whenClickClearFiltersCache_shouldShowSuccessToast() {
        given {
            launchWithSuccess()
        } withAction {
            clickClearFiltersCache()
        } then {
            showFiltersCacheClearedToast()
        }
    }

    @Test
    fun whenClickFilter_shouldNavigateToCardList() {
        given {
            launchWithSuccess()
        } withAction {
            clickFilter()
        } then {
            navigateToCardList()
        }
    }
}
