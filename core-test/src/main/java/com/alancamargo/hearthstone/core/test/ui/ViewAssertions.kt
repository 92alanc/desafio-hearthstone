package com.alancamargo.hearthstone.core.test.ui

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

fun assertTextIsDisplayed(text: String) {
    onView(withText(text)).check(matches(isDisplayed()))
}

fun assertViewIsDisplayed(@IdRes viewId: Int) {
    onView(withId(viewId)).check(matches(isDisplayed()))
}
