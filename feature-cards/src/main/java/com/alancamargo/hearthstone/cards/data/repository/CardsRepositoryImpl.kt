package com.alancamargo.hearthstone.cards.data.repository

import com.alancamargo.hearthstone.cards.data.local.CardsLocalDataSource
import com.alancamargo.hearthstone.cards.data.remote.CardsRemoteDataSource
import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.cards.domain.repository.CardsRepository
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.core.log.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CardsRepositoryImpl @Inject constructor(
    private val remoteDataSource: CardsRemoteDataSource,
    private val localDataSource: CardsLocalDataSource,
    private val logger: Logger
) : CardsRepository {

    override fun getCards(type: FilterType, filter: String): Flow<CardListResult> = flow {
        val remoteResult = remoteDataSource.getCards(type, filter)

        if (remoteResult is CardListResult.Success) {
            logger.debug("Saving cards...")
            remoteResult.cards.forEach { localDataSource.saveCard(it) }
            emit(remoteResult)
        } else {
            logger.debug("Fetching cards from database...")
            val localResult = localDataSource.getCards(type, filter)

            if (localResult is CardListResult.Success) {
                emit(localResult)
            } else {
                logger.debug("No cards in database")
                emit(remoteResult)
            }
        }
    }

    override fun clearCache(): Flow<Unit> = flow {
        logger.debug("Clearing cards cache...")
        val task = localDataSource.deleteCards()
        emit(task)
    }
}
