package com.alancamargo.hearthstone.cards.domain.usecase

import app.cash.turbine.test
import com.alancamargo.hearthstone.cards.domain.repository.CardsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ClearCardsCacheUseCaseImplTest {

    private val mockRepository = mockk<CardsRepository>()
    private val useCase = ClearCardsCacheUseCaseImpl(mockRepository)

    @Test
    fun `invoke should return task result from repository`() {
        runBlocking {
            // GIVEN
            every { mockRepository.clearCache() } returns flowOf(Unit)

            // WHEN
            val result = useCase()

            // THEN
            result.test {
                awaitItem()
                awaitComplete()
            }
        }
    }
}
