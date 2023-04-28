package com.alancamargo.hearthstone.filters.ui.robots

import androidx.test.core.app.ActivityScenario
import com.alancamargo.hearthstone.core.test.web.delayWebResponse
import com.alancamargo.hearthstone.core.test.web.disconnect
import com.alancamargo.hearthstone.core.test.web.mockWebError
import com.alancamargo.hearthstone.core.test.web.mockWebResponse
import com.alancamargo.hearthstone.filters.ui.FiltersActivity
import com.alancamargo.hearthstone.filters.ui.FiltersActivityTest
import java.net.HttpURLConnection

internal fun FiltersActivityTest.given(
    block: FiltersActivityTestRobot.() -> Unit
): FiltersActivityTestRobot = FiltersActivityTestRobot(testSuite = this).apply(block)

internal class FiltersActivityTestRobot(private val testSuite: FiltersActivityTest) {

    fun launchWithSuccess() {
        launchWithPrecondition {
            mockWebResponse(jsonAssetPath = "filters_success.json")
        }
    }

    fun launchWithDelayedWebResponse() {
        launchWithPrecondition {
            delayWebResponse()
        }
    }

    fun launchDisconnected() {
        launchWithPrecondition {
            disconnect()
        }
    }

    fun launchWithGenericError() {
        launchWithPrecondition {
            mockWebError(HttpURLConnection.HTTP_NOT_FOUND)
        }
    }

    infix fun withAction(
        action: FiltersActivityActionRobot.() -> Unit
    ): FiltersActivityActionRobot {
        return FiltersActivityActionRobot(testSuite).apply(action)
    }

    infix fun then(
        assertion: FiltersActivityAssertionRobot.() -> Unit
    ): FiltersActivityAssertionRobot {
        return FiltersActivityAssertionRobot(testSuite).apply(assertion)
    }

    private fun launchWithPrecondition(beforeLaunch: () -> Unit) {
        beforeLaunch()
        launch()
    }

    private fun launch() {
        ActivityScenario.launch(FiltersActivity::class.java)
    }
}
