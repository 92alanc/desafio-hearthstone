package com.alancamargo.hearthstone.filters.domain.usecase

import app.cash.turbine.test
import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import com.alancamargo.hearthstone.filters.domain.repository.FiltersRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetFiltersUseCaseImplTest {

    private val mockRepository = mockk<FiltersRepository>()
    private val useCase = GetFiltersUseCaseImpl(mockRepository)

    @Test
    fun `invoke should return result from repository`() {
        runBlocking {
            // GIVEN
            every { mockRepository.getFilters() } returns flowOf(FiltersResult.GenericError)

            // WHEN
            val result = useCase()

            // THEN
            result.test {
                val actual = awaitItem()
                assertThat(actual).isInstanceOf(FiltersResult.GenericError::class.java)
                awaitComplete()
            }
        }
    }
}
