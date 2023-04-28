package com.alancamargo.hearthstone.cards.ui.viewmodel

internal sealed class CardsViewAction {

    object ShowCardsCacheClearedToast : CardsViewAction()

    object ShowErrorClearingCardsCacheToast : CardsViewAction()

    object Finish : CardsViewAction()
}
