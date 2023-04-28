package com.alancamargo.hearthstone.cards.domain.usecase

import app.cash.turbine.test
import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.cards.domain.repository.CardsRepository
import com.alancamargo.hearthstone.core.domain.FilterType
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCardsUseCaseImplTest {

    private val mockRepository = mockk<CardsRepository>()
    private val useCase = GetCardsUseCaseImpl(mockRepository)

    @Test
    fun `invoke should return result from repository`() {
        runBlocking {
            // GIVEN
            every {
                mockRepository.getCards(type = any(), filter = any())
            } returns flowOf(CardListResult.GenericError)

            // WHEN
            val result = useCase(FilterType.RACE, filter = "Filter")

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(CardListResult.GenericError::class.java)
                awaitComplete()
            }
        }
    }
}
