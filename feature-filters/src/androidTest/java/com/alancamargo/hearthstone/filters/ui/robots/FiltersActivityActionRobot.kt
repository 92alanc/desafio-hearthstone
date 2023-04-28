package com.alancamargo.hearthstone.filters.ui.robots

import com.alancamargo.hearthstone.core.test.ui.clickMenuItem
import com.alancamargo.hearthstone.core.test.ui.performClick
import com.alancamargo.hearthstone.filters.ui.FiltersActivityTest

internal class FiltersActivityActionRobot(private val testSuite: FiltersActivityTest) {

    fun clickAppInfo() {
        clickMenuItem(itemText = "App info")
    }

    fun clickClearFiltersCache() {
        clickMenuItem(itemText = "Clear filters cache")
    }

    fun clickFilter() {
        performClick(text = "Horde")
    }

    infix fun then(
        assertion: FiltersActivityAssertionRobot.() -> Unit
    ): FiltersActivityAssertionRobot {
        return FiltersActivityAssertionRobot(testSuite).apply(assertion)
    }
}
