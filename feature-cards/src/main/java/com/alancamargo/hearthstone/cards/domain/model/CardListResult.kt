package com.alancamargo.hearthstone.cards.domain.model

import com.alancamargo.hearthstone.core.domain.Card

internal sealed class CardListResult {

    data class Success(val cards: List<Card>) : CardListResult()

    object NetworkError : CardListResult()

    object GenericError : CardListResult()
}
