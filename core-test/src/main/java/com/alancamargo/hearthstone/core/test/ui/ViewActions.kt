package com.alancamargo.hearthstone.core.test.ui

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.allOf

fun performClick(@IdRes viewId: Int) {
    onView(
        allOf(
            withId(viewId),
            isDisplayed()
        )
    ).perform(click())
}

fun performClick(text: String) {
    onView(
        allOf(
            withText(text),
            isDisplayed()
        )
    ).perform(click())
}

fun clickMenuItem(itemText: String) {
    openActionBarOverflowOrOptionsMenu(
        InstrumentationRegistry.getInstrumentation().targetContext
    )

    performClick(itemText)
}
