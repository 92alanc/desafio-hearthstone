package com.alancamargo.hearthstone.cards.ui.viewmodel

internal sealed class CardsViewAction {

    data class ShowCardImage(val imageUrl: String) : CardsViewAction()

    object ShowCardsCacheClearedToast : CardsViewAction()

    object ShowErrorClearingCardsCacheToast : CardsViewAction()

    object Finish : CardsViewAction()
}
