package com.alancamargo.hearthstone.cards.ui.viewmodel

import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.cards.domain.usecase.ClearCardsCacheUseCase
import com.alancamargo.hearthstone.cards.domain.usecase.GetCardsUseCase
import com.alancamargo.hearthstone.cards.testtools.stubCard
import com.alancamargo.hearthstone.cards.ui.model.UiCardsError
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.core.log.Logger
import com.alancamargo.hearthstone.core.test.viewmodel.ViewModelFlowCollector
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
class CardsViewModelTest {

    private val mockGetCardsUseCase = mockk<GetCardsUseCase>()
    private val mockClearCardsCacheUseCase = mockk<ClearCardsCacheUseCase>()
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val testDispatcher = TestCoroutineDispatcher()

    private val viewModel = CardsViewModel(
        mockGetCardsUseCase,
        mockClearCardsCacheUseCase,
        mockLogger,
        testDispatcher
    )

    private val collector = ViewModelFlowCollector(
        stateFlow = viewModel.state,
        actionFlow = viewModel.action,
        dispatcher = testDispatcher
    )

    @Test
    fun `when use case returns Success loadCards should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every {
                mockGetCardsUseCase(type = any(), filter = any())
            } returns flowOf(CardListResult.Success(cards = emptyList()))

            // WHEN
            viewModel.loadCards(type = FilterType.FACTION, filter = "Central")

            // THEN
            val expected = listOf(
                CardsViewState(isLoading = true),
                CardsViewState(cards = emptyList())
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case returns NetworkError loadCards should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every {
                mockGetCardsUseCase(type = any(), filter = any())
            } returns flowOf(CardListResult.NetworkError)

            // WHEN
            viewModel.loadCards(type = FilterType.FACTION, filter = "Central")

            // THEN
            val expected = listOf(
                CardsViewState(isLoading = true),
                CardsViewState(error = UiCardsError.NETWORK)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case returns GenericError loadCards should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every {
                mockGetCardsUseCase(type = any(), filter = any())
            } returns flowOf(CardListResult.GenericError)

            // WHEN
            viewModel.loadCards(type = FilterType.FACTION, filter = "Central")

            // THEN
            val expected = listOf(
                CardsViewState(isLoading = true),
                CardsViewState(error = UiCardsError.GENERIC)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws exception getCards should set correct states`() {
        collector.test { states, _ ->
            // GIVEN
            every {
                mockGetCardsUseCase(type = any(), filter = any())
            } returns flow { throw IllegalStateException() }

            // WHEN
            viewModel.loadCards(type = FilterType.FACTION, filter = "Central")

            // THEN
            val expected = listOf(
                CardsViewState(isLoading = true),
                CardsViewState(error = UiCardsError.GENERIC)
            )
            assertThat(states).containsAtLeastElementsIn(expected)
        }
    }

    @Test
    fun `when use case throws exception getCards should log error`() {
        // GIVEN
        val exception = IllegalArgumentException()
        every {
            mockGetCardsUseCase(type = any(), filter = any())
        } returns flow { throw exception }

        // WHEN
        viewModel.loadCards(type = FilterType.FACTION, filter = "Central")

        // THEN
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when clearing cards cache succeeds onClearCardsCacheClicked should send ShowCardsCacheClearedToast action`() {
        collector.test { _, actions ->
            // GIVEN
            every { mockClearCardsCacheUseCase() } returns flowOf(Unit)

            // WHEN
            viewModel.onClearCardsCacheClicked()

            // THEN
            val expected = CardsViewAction.ShowCardsCacheClearedToast
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `when clearing cards cache fails onClearCardsCacheClicked should send ShowErrorClearingCardsCacheToast action`() {
        collector.test { _, actions ->
            // GIVEN
            every { mockClearCardsCacheUseCase() } returns flow { throw IOException() }

            // WHEN
            viewModel.onClearCardsCacheClicked()

            // THEN
            val expected = CardsViewAction.ShowErrorClearingCardsCacheToast
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `when card has image onCardClicked should send ShowCardImage action`() {
        collector.test { _, actions ->
            // WHEN
            val imageUrl = "https://test.com/racionais"
            val card = stubCard(imageUrl)
            viewModel.onCardClicked(card)

            // THEN
            val expected = CardsViewAction.ShowCardImage(imageUrl)
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `when card does not have image onCardClicked should send ShowNoImageAvailableToast action`() {
        collector.test { _, actions ->
            // WHEN
            val card = stubCard()
            viewModel.onCardClicked(card)

            // THEN
            val expected = CardsViewAction.ShowNoImageAvailableToast
            assertThat(actions).contains(expected)
        }
    }

    @Test
    fun `onBackClicked should send Finish action`() {
        collector.test { _, actions ->
            // WHEN
            viewModel.onBackClicked()

            // THEN
            val expected = CardsViewAction.Finish
            assertThat(actions).contains(expected)
        }
    }
}
