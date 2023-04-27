package com.alancamargo.hearthstone.cards.ui.viewmodel

import com.alancamargo.hearthstone.core.domain.Card

internal sealed class CardsViewAction {

    data class ShowCardImage(val imageUrl: String) : CardsViewAction()

    data class ShowCardDetails(val card: Card) : CardsViewAction()

    object ShowCardsCacheClearedToast : CardsViewAction()

    object ShowErrorClearingCardsCacheToast : CardsViewAction()

    object Finish : CardsViewAction()
}
