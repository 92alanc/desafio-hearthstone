package com.alancamargo.hearthstone.filters.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

internal enum class UiFiltersError(
    @DrawableRes val iconRes: Int,
    @StringRes val messageRes: Int
) {

    NETWORK(
        iconRes = 0,
        messageRes = 0
    ),

    GENERIC(
        iconRes = 0,
        messageRes = 0
    )
}
