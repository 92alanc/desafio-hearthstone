package com.alancamargo.hearthstone.cards.data.local

import com.alancamargo.hearthstone.cards.data.database.CardsDao
import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.core.data.mapping.toDb
import com.alancamargo.hearthstone.core.data.mapping.toDomain
import com.alancamargo.hearthstone.core.domain.Card
import com.alancamargo.hearthstone.core.domain.FilterType
import javax.inject.Inject

internal class CardsLocalDataSourceImpl @Inject constructor(
    private val dao: CardsDao
) : CardsLocalDataSource {

    override suspend fun getCards(type: FilterType, filter: String): CardListResult {
        val databaseResponse = when (type) {
            FilterType.FACTION -> dao.getCardsByFaction(filter)
            FilterType.TYPE -> dao.getCardsByType(filter)
            FilterType.RACE -> dao.getCardsByRace(filter)
            FilterType.QUALITY -> dao.getCardsByQuality(filter)
            FilterType.PLAYER_CLASS -> dao.getCardsByPlayerClass(filter)
        }

        return databaseResponse?.let {
            val cards = it.map { card -> card.toDomain() }
            CardListResult.Success(cards)
        } ?: CardListResult.GenericError
    }

    override suspend fun saveCard(card: Card) {
        val dbCard = card.toDb()

        if (dao.getCardsCount(dbCard.id) > 0) {
            dao.updateCard(dbCard)
        } else {
            dao.insertCard(dbCard)
        }
    }

    override suspend fun deleteCards() {
        dao.deleteCards()
    }
}
