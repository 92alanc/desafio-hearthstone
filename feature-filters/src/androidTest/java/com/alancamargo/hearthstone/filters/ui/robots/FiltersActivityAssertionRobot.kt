package com.alancamargo.hearthstone.filters.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.core.test.ui.assertTextIsDisplayed
import com.alancamargo.hearthstone.core.test.ui.assertViewIsDisplayed
import com.alancamargo.hearthstone.core.test.ui.withRecyclerViewItemCount
import com.alancamargo.hearthstone.filters.R
import com.alancamargo.hearthstone.filters.ui.FiltersActivityTest
import io.mockk.verify

internal class FiltersActivityAssertionRobot(private val testSuite: FiltersActivityTest) {

    fun filterBlockCountIs(count: Int) {
        onView(withId(R.id.recyclerView)).check(withRecyclerViewItemCount(count))
    }

    fun navigateToCardList() {
        verify {
            testSuite.mockCardListActivityNavigation.startActivity(
                context = any(),
                filter = "Horde",
                type = FilterType.FACTION
            )
        }
    }

    fun showAppInfo() {
        verify {
            testSuite.mockDialogueHelper.showDialogue(
                context = any(),
                titleRes = R.string.app_info_label,
                messageRes = R.string.app_info
            )
        }
    }

    fun showFiltersCacheClearedToast() {
        verify {
            testSuite.mockToastHelper.showToast(R.string.filters_cache_cleared)
        }
    }

    fun showShimmer() {
        assertViewIsDisplayed(R.id.view1)
    }

    fun showNetworkError() {
        assertTextIsDisplayed("Please check your internet connection and try again")
    }

    fun showGenericError() {
        assertTextIsDisplayed("Something went wrong. Please try again later")
    }
}
