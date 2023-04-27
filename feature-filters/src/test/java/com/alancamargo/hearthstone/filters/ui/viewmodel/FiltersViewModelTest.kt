package com.alancamargo.hearthstone.filters.ui.viewmodel

import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.core.log.Logger
import com.alancamargo.hearthstone.core.test.viewmodel.ViewModelFlowCollector
import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import com.alancamargo.hearthstone.filters.domain.usecase.ClearFiltersCacheUseCase
import com.alancamargo.hearthstone.filters.domain.usecase.GetFiltersUseCase
import com.alancamargo.hearthstone.filters.testtools.stubFilters
import com.alancamargo.hearthstone.filters.ui.model.UiFiltersError
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class FiltersViewModelTest {

    private val mockGetFiltersUseCase = mockk<GetFiltersUseCase>()
    private val mockClearFiltersCacheUseCase = mockk<ClearFiltersCacheUseCase>()
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val testDispatcher = TestCoroutineDispatcher()

    private val viewModel = FiltersViewModel(
        mockGetFiltersUseCase,
        mockClearFiltersCacheUseCase,
        mockLogger,
        testDispatcher
    )

    private val collector = ViewModelFlowCollector(
        stateFlow = viewModel.state,
        actionFlow = viewModel.action,
        dispatcher = testDispatcher
    )

    @Test
    fun `when use case returns Success getFilters should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            val filters = stubFilters()
            val result = FiltersResult.Success(filters)
            every { mockGetFiltersUseCase() } returns flowOf(result)

            // WHEN
            viewModel.loadFilters()

            // THEN
            val expected = listOf(
                FiltersViewState(isLoading = true),
                FiltersViewState(filters = filters)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case returns GenericError getFilters should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every { mockGetFiltersUseCase() } returns flowOf(FiltersResult.GenericError)

            // WHEN
            viewModel.loadFilters()

            // THEN
            val expected = listOf(
                FiltersViewState(isLoading = true),
                FiltersViewState(error = UiFiltersError.GENERIC)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws IOException getFilters should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every { mockGetFiltersUseCase() } returns flow { throw IOException() }

            // WHEN
            viewModel.loadFilters()

            // THEN
            val expected = listOf(
                FiltersViewState(isLoading = true),
                FiltersViewState(error = UiFiltersError.NETWORK)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws generic exception getFilters should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every { mockGetFiltersUseCase() } returns flow { throw IllegalStateException() }

            // WHEN
            viewModel.loadFilters()

            // THEN
            val expected = listOf(
                FiltersViewState(isLoading = true),
                FiltersViewState(error = UiFiltersError.GENERIC)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws exception getFilters should log error`() {
        // GIVEN
        val exception = IllegalArgumentException()
        every { mockGetFiltersUseCase() } returns flow { throw exception }

        // WHEN
        viewModel.loadFilters()

        // THEN
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when clearing filters cache succeeds onClearFiltersCacheClicked should send ShowFiltersCacheClearedToast action`() {
        collector.test { _, actions ->
            // GIVEN
            every { mockClearFiltersCacheUseCase() } returns flowOf(Unit)

            // WHEN
            viewModel.onClearFiltersCacheClicked()

            // THEN
            val expected = FiltersViewAction.ShowFiltersCacheClearedToast
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `when clearing filters cache fails onClearFiltersCacheClicked should send ShowErrorClearingFiltersCacheToast action`() {
        collector.test { _, actions ->
            // GIVEN
            every { mockClearFiltersCacheUseCase() } returns flow { throw IOException() }

            // WHEN
            viewModel.onClearFiltersCacheClicked()

            // THEN
            val expected = FiltersViewAction.ShowErrorClearingFiltersCacheToast
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `onFilterClicked should send NavigateToCardsList action`() {
        collector.test { _, actions ->
            // WHEN
            val filter = "My Filter"
            val type = FilterType.FACTION
            viewModel.onFilterClicked(filter, type)

            // THEN
            val expected = FiltersViewAction.NavigateToCardsList(filter, type)
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `onAppInfoClicked should send ShowAppInfo action`() {
        collector.test { _, actions ->
            // WHEN
            viewModel.onAppInfoClicked()

            // THEN
            val expected = FiltersViewAction.ShowAppInfo
            assertThat(actions).contains(expected)
        }
    }
}
