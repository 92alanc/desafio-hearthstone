package com.alancamargo.hearthstone.cards.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.hearthstone.cards.domain.model.CardListResult
import com.alancamargo.hearthstone.cards.domain.usecase.ClearCardsCacheUseCase
import com.alancamargo.hearthstone.cards.domain.usecase.GetCardsUseCase
import com.alancamargo.hearthstone.cards.ui.model.UiCardsError
import com.alancamargo.hearthstone.core.di.IoDispatcher
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.core.log.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CardsViewModel @Inject constructor(
    private val getCardsUseCase: GetCardsUseCase,
    private val clearCardsCacheUseCase: ClearCardsCacheUseCase,
    private val logger: Logger,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(CardsViewState())
    private val _action = MutableSharedFlow<CardsViewAction>()

    val state: StateFlow<CardsViewState> = _state
    val action: SharedFlow<CardsViewAction> = _action

    fun loadCards(type: FilterType, filter: String) {
        viewModelScope.launch(dispatcher) {
            getCardsUseCase(type, filter).onStart {
                _state.update { it.onLoading() }
            }.onCompletion {
                _state.update { it.onFinishedLoading() }
            }.catch {
                logger.error(it)
                _state.update { state -> state.onError(UiCardsError.GENERIC) }
            }.collect { result ->
                when (result) {
                    is CardListResult.Success -> _state.update {
                        val cards = result.cards
                        it.onCardsReceived(cards)
                    }

                    is CardListResult.NetworkError -> _state.update {
                        it.onError(UiCardsError.NETWORK)
                    }

                    is CardListResult.GenericError -> _state.update {
                        it.onError(UiCardsError.GENERIC)
                    }
                }
            }
        }
    }

    fun onClearCardsCacheClicked() {
        viewModelScope.launch(dispatcher) {
            clearCardsCacheUseCase().catch {
                logger.error(it)
                _action.emit(CardsViewAction.ShowErrorClearingCardsCacheToast)
            }.collect {
                _action.emit(CardsViewAction.ShowCardsCacheClearedToast)
            }
        }
    }

    fun onBackClicked() {
        viewModelScope.launch(dispatcher) {
            _action.emit(CardsViewAction.Finish)
        }
    }
}
