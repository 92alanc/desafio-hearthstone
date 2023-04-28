package com.alancamargo.hearthstone.cards.data.local

import com.alancamargo.hearthstone.cards.data.database.CardsDao
import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.cards.testtools.stubCard
import com.alancamargo.hearthstone.core.domain.FilterType
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CardsLocalDataSourceImplTest {

    private val mockDao = mockk<CardsDao>(relaxed = true)
    private val localDataSource = CardsLocalDataSourceImpl(mockDao)

    @Test
    fun `when filter type is FACTION getCards should filter cards by faction`() {
        // WHEN
        val filter = "Test Filter"
        runBlocking { localDataSource.getCards(type = FilterType.FACTION, filter) }

        // THEN
        coVerify { mockDao.getCardsByFaction(filter) }
    }

    @Test
    fun `when filter type is TYPE getCards should filter cards by type`() {
        // WHEN
        val filter = "Test Filter"
        runBlocking { localDataSource.getCards(type = FilterType.TYPE, filter) }

        // THEN
        coVerify { mockDao.getCardsByType(filter) }
    }

    @Test
    fun `when filter type is RACE getCards should filter cards by race`() {
        // WHEN
        val filter = "Test Filter"
        runBlocking { localDataSource.getCards(type = FilterType.RACE, filter) }

        // THEN
        coVerify { mockDao.getCardsByRace(filter) }
    }

    @Test
    fun `when filter type is QUALITY getCards should filter cards by quality`() {
        // WHEN
        val filter = "Test Filter"
        runBlocking { localDataSource.getCards(type = FilterType.QUALITY, filter) }

        // THEN
        coVerify { mockDao.getCardsByQuality(filter) }
    }

    @Test
    fun `when filter type is PLAYER_CLASS getCards should filter cards by player class`() {
        // WHEN
        val filter = "Test Filter"
        runBlocking { localDataSource.getCards(type = FilterType.PLAYER_CLASS, filter) }

        // THEN
        coVerify { mockDao.getCardsByPlayerClass(filter) }
    }

    @Test
    fun `when database returns cards getCards should return Success`() {
        // GIVEN
        coEvery { mockDao.getCardsByFaction(faction = any()) } returns emptyList()

        // WHEN
        val result = runBlocking {
            localDataSource.getCards(type = FilterType.FACTION, filter = "Test Filter")
        }

        // THEN
        assertThat(result).isInstanceOf(CardListResult.Success::class.java)
    }

    @Test
    fun `when database returns null getCards should return GenericError`() {
        // GIVEN
        coEvery { mockDao.getCardsByFaction(faction = any()) } returns null

        // WHEN
        val result = runBlocking {
            localDataSource.getCards(type = FilterType.FACTION, filter = "Test Filter")
        }

        // THEN
        assertThat(result).isInstanceOf(CardListResult.GenericError::class.java)
    }

    @Test
    fun `when card exists saveCard should update existing record`() {
        // GIVEN
        coEvery { mockDao.getCardsCount(name = any()) } returns 1

        // WHEN
        val card = stubCard()
        runBlocking { localDataSource.saveCard(card) }

        // THEN
        coVerify { mockDao.updateCard(card = any()) }
    }

    @Test
    fun `when card does not exist saveCard should insert a new record`() {
        // GIVEN
        coEvery { mockDao.getCardsCount(name = any()) } returns 0

        // WHEN
        val card = stubCard()
        runBlocking { localDataSource.saveCard(card) }

        // THEN
        coVerify { mockDao.insertCard(card = any()) }
    }

    @Test
    fun `deleteCards should delete cards from database`() {
        // WHEN
        runBlocking { localDataSource.deleteCards() }

        // THEN
        coVerify { mockDao.deleteCards() }
    }
}
