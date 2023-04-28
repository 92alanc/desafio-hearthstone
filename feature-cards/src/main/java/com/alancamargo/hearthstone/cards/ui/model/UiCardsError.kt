package com.alancamargo.hearthstone.cards.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alancamargo.hearthstone.core.design.R

internal enum class UiCardsError(
    @DrawableRes val iconRes: Int,
    @StringRes val messageRes: Int
) {

    NETWORK(
        iconRes = R.drawable.ic_network_error,
        messageRes = R.string.message_network_error
    ),

    GENERIC(
        iconRes = R.drawable.ic_generic_error,
        messageRes = R.string.message_generic_error
    )
}
