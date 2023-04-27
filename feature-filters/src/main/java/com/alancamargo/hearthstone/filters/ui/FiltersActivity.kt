package com.alancamargo.hearthstone.filters.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.alancamargo.hearthstone.core.design.tools.DialogueHelper
import com.alancamargo.hearthstone.core.design.tools.ToastHelper
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.core.extensions.observeViewModelFlow
import com.alancamargo.hearthstone.filters.R
import com.alancamargo.hearthstone.filters.databinding.ActivityFiltersBinding
import com.alancamargo.hearthstone.filters.ui.adapter.FilterBlockAdapter
import com.alancamargo.hearthstone.filters.ui.viewmodel.FiltersViewAction
import com.alancamargo.hearthstone.filters.ui.viewmodel.FiltersViewModel
import com.alancamargo.hearthstone.filters.ui.viewmodel.FiltersViewState
import com.alancamargo.hearthstone.navigation.CardsActivityNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class FiltersActivity : AppCompatActivity() {

    private var _binding: ActivityFiltersBinding? = null
    private val binding: ActivityFiltersBinding
        get() = _binding!!

    private val adapter by lazy {
        FilterBlockAdapter { filter, type ->
            viewModel.onFilterClicked(filter, type)
        }
    }

    private val viewModel by viewModels<FiltersViewModel>()

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var dialogueHelper: DialogueHelper

    @Inject
    lateinit var cardsActivityNavigation: CardsActivityNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFiltersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeViewModelFlow(viewModel.state, ::onStateChanged)
        observeViewModelFlow(viewModel.action, ::onAction)
        viewModel.loadFilters()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filters, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemAppInfo -> viewModel.onAppInfoClicked()
            R.id.itemClearFiltersCache -> viewModel.onClearFiltersCacheClicked()
        }

        return true
    }

    private fun setUpUi() = with(binding) {
        setSupportActionBar(toolbar)
        recyclerView.adapter = adapter
        btTryAgain.setOnClickListener {
            viewModel.loadFilters()
        }
    }

    private fun onStateChanged(state: FiltersViewState) = with(state) {
        binding.shimmerContainer.isVisible = isLoading
        binding.recyclerView.isVisible = !isLoading && filters != null
        filters?.let(adapter::submitList)
        binding.groupError.isVisible = error != null

        error?.let {
            binding.imgError.setImageResource(it.iconRes)
            binding.txtError.setText(it.messageRes)
        }
    }

    private fun onAction(action: FiltersViewAction) {
        when (action) {
            is FiltersViewAction.ShowAppInfo -> showAppInfo()

            is FiltersViewAction.NavigateToCardsList -> navigateToCardsList(
                action.filter,
                action.type
            )

            is FiltersViewAction.ShowFiltersCacheClearedToast -> showFiltersCacheClearedToast()

            is FiltersViewAction.ShowErrorClearingFiltersCacheToast -> {
                showErrorClearingFiltersCacheToast()
            }
        }
    }

    private fun showAppInfo() {
        dialogueHelper.showDialogue(
            context = this,
            titleRes = R.string.app_info_label,
            messageRes = R.string.app_info
        )
    }

    private fun navigateToCardsList(filter: String, type: FilterType) {
        cardsActivityNavigation.startActivity(
            context = this,
            filter = filter,
            type = type
        )
    }

    private fun showFiltersCacheClearedToast() {
        toastHelper.showToast(R.string.filters_cache_cleared)
    }

    private fun showErrorClearingFiltersCacheToast() {
        toastHelper.showToast(R.string.error_clearing_filters_cache)
    }
}
