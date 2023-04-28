package com.alancamargo.hearthstone.cards.data.repository

import app.cash.turbine.test
import com.alancamargo.hearthstone.cards.data.local.CardsLocalDataSource
import com.alancamargo.hearthstone.cards.data.remote.CardsRemoteDataSource
import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.cards.testtools.stubCard
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.core.log.Logger
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CardsRepositoryImplTest {

    private val mockRemoteDataSource = mockk<CardsRemoteDataSource>()
    private val mockLocalDataSource = mockk<CardsLocalDataSource>(relaxed = true)
    private val mockLogger = mockk<Logger>(relaxed = true)

    private val repository = CardsRepositoryImpl(
        mockRemoteDataSource,
        mockLocalDataSource,
        mockLogger
    )

    @Test
    fun `when remote returns Success getCards should return Success`() {
        runBlocking {
            // GIVEN
            coEvery {
                mockRemoteDataSource.getCards(type = any(), filter = any())
            } returns CardListResult.Success(cards = emptyList())

            // WHEN
            val result = repository.getCards(type = FilterType.RACE, filter = "Filter")

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(CardListResult.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun `when remote returns Success getCards should save cards in local`() {
        runBlocking {
            // GIVEN
            coEvery {
                mockRemoteDataSource.getCards(type = any(), filter = any())
            } returns CardListResult.Success(cards = listOf(stubCard()))

            // WHEN
            val result = repository.getCards(type = FilterType.RACE, filter = "Filter")

            // THEN
            result.test {
                awaitItem()
                coVerify { mockLocalDataSource.saveCard(card = any()) }
                awaitComplete()
            }
        }
    }

    @Test
    fun `when remote returns Success getCards should log saving cards`() {
        runBlocking {
            // GIVEN
            coEvery {
                mockRemoteDataSource.getCards(type = any(), filter = any())
            } returns CardListResult.Success(cards = listOf(stubCard()))

            // WHEN
            val result = repository.getCards(type = FilterType.RACE, filter = "Filter")

            // THEN
            result.test {
                awaitItem()
                verify { mockLogger.debug("Saving cards...") }
                awaitComplete()
            }
        }
    }

    @Test
    fun `when remote returns GenericError and local returns Success getCards should return Success`() {
        runBlocking {
            // GIVEN
            coEvery {
                mockRemoteDataSource.getCards(type = any(), filter = any())
            } returns CardListResult.GenericError
            coEvery {
                mockLocalDataSource.getCards(type = any(), filter = any())
            } returns CardListResult.Success(cards = emptyList())

            // WHEN
            val result = repository.getCards(type = FilterType.RACE, filter = "Filter")

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(CardListResult.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun `when both remote and local return GenericError getCards should return GenericError`() {
        runBlocking {
            // GIVEN
            coEvery {
                mockRemoteDataSource.getCards(type = any(), filter = any())
            } returns CardListResult.GenericError
            coEvery {
                mockLocalDataSource.getCards(type = any(), filter = any())
            } returns CardListResult.GenericError

            // WHEN
            val result = repository.getCards(type = FilterType.RACE, filter = "Filter")

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(CardListResult.GenericError::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun `when remote returns GenericError getCards should log fetching from local`() {
        runBlocking {
            // GIVEN
            coEvery {
                mockRemoteDataSource.getCards(type = any(), filter = any())
            } returns CardListResult.GenericError

            // WHEN
            val result = repository.getCards(type = FilterType.RACE, filter = "Filter")

            // THEN
            result.test {
                awaitItem()
                verify { mockLogger.debug("Fetching cards from database...") }
                awaitComplete()
            }
        }
    }

    @Test
    fun `when both remote and local return GenericError getCards should log no cards in database`() {
        runBlocking {
            // GIVEN
            coEvery {
                mockRemoteDataSource.getCards(type = any(), filter = any())
            } returns CardListResult.GenericError
            coEvery {
                mockLocalDataSource.getCards(type = any(), filter = any())
            } returns CardListResult.GenericError

            // WHEN
            val result = repository.getCards(type = FilterType.RACE, filter = "Filter")

            // THEN
            result.test {
                awaitItem()
                verify { mockLogger.debug("No cards in database") }
                awaitComplete()
            }
        }
    }

    @Test
    fun `clearCache should emit task result`() {
        runBlocking {
            // WHEN
            val result = repository.clearCache()

            // THEN
            result.test {
                awaitItem()
                awaitComplete()
            }
        }
    }

    @Test
    fun `clearCache should log clearing cards cache`() {
        runBlocking {
            // WHEN
            val result = repository.clearCache()

            // THEN
            result.test {
                awaitItem()
                verify { mockLogger.debug("Clearing cards cache...") }
                awaitComplete()
            }
        }
    }
}
