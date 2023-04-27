package com.alancamargo.hearthstone.filters.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.hearthstone.core.di.IoDispatcher
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.core.log.Logger
import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import com.alancamargo.hearthstone.filters.domain.usecase.ClearFiltersCacheUseCase
import com.alancamargo.hearthstone.filters.domain.usecase.GetFiltersUseCase
import com.alancamargo.hearthstone.filters.ui.model.UiFiltersError
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
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
internal class FiltersViewModel @Inject constructor(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val clearFiltersCacheUseCase: ClearFiltersCacheUseCase,
    private val logger: Logger,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(FiltersViewState())
    private val _action = MutableSharedFlow<FiltersViewAction>()

    val state: StateFlow<FiltersViewState> = _state
    val action: SharedFlow<FiltersViewAction> = _action

    fun loadFilters() {
        viewModelScope.launch(dispatcher) {
            getFiltersUseCase().onStart {
                _state.update { it.onLoading() }
            }.onCompletion {
                _state.update { it.onFinishedLoading() }
            }.catch {
                logger.error(it)

                val error = if (it is IOException) {
                    UiFiltersError.NETWORK
                } else {
                    UiFiltersError.GENERIC
                }

                _state.update { state -> state.onError(error) }
            }.collect { result ->
                when (result) {
                    is FiltersResult.Success -> _state.update {
                        it.onFiltersReceived(result.filters)
                    }

                    is FiltersResult.GenericError -> _state.update {
                        it.onError(UiFiltersError.GENERIC)
                    }
                }
            }
        }
    }

    fun onClearFiltersCacheClicked() {
        viewModelScope.launch(dispatcher) {
            clearFiltersCacheUseCase().catch {
                logger.error(it)
                _action.emit(FiltersViewAction.ShowErrorClearingFiltersCacheToast)
            }.collect {
                _action.emit(FiltersViewAction.ShowFiltersCacheClearedToast)
            }
        }
    }

    fun onFilterClicked(filter: String, type: FilterType) {
        viewModelScope.launch(dispatcher) {
            _action.emit(FiltersViewAction.NavigateToCardsList(filter, type))
        }
    }

    fun onAppInfoClicked() {
        viewModelScope.launch(dispatcher) {
            _action.emit(FiltersViewAction.ShowAppInfo)
        }
    }
}
