package com.alancamargo.hearthstone.core.design.tools

import androidx.annotation.StringRes

interface ToastHelper {

    fun showToast(@StringRes textRes: Int)

    fun showToast(text: String)
}
