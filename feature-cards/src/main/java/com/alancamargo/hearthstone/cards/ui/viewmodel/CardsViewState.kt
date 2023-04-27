package com.alancamargo.hearthstone.cards.ui.viewmodel

import com.alancamargo.hearthstone.cards.ui.model.UiCard
import com.alancamargo.hearthstone.cards.ui.model.UiCardsError

internal data class CardsViewState(
    val isLoading: Boolean = false,
    val cards: List<UiCard>? = null,
    val error: UiCardsError? = null
) {

    fun onLoading() = copy(
        isLoading = true,
        error = null
    )

    fun onFinishedLoading() = copy(isLoading = false)

    fun onCardsReceived(cards: List<UiCard>) = copy(
        cards = cards,
        error = null
    )

    fun onError(error: UiCardsError) = copy(error = error)
}
