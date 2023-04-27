package com.alancamargo.hearthstone.filters.domain.usecase

import app.cash.turbine.test
import com.alancamargo.hearthstone.filters.domain.repository.FiltersRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ClearFiltersCacheUseCaseImplTest {

    private val mockRepository = mockk<FiltersRepository>()
    private val useCase = ClearFiltersCacheUseCaseImpl(mockRepository)

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
