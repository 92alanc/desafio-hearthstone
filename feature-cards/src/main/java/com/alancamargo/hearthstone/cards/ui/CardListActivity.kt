package com.alancamargo.hearthstone.cards.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.alancamargo.hearthstone.cards.R
import com.alancamargo.hearthstone.cards.databinding.ActivityCardListBinding
import com.alancamargo.hearthstone.cards.ui.adapter.CardAdapter
import com.alancamargo.hearthstone.cards.ui.viewmodel.CardsViewAction
import com.alancamargo.hearthstone.cards.ui.viewmodel.CardsViewModel
import com.alancamargo.hearthstone.cards.ui.viewmodel.CardsViewState
import com.alancamargo.hearthstone.core.design.tools.ToastHelper
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.core.extensions.args
import com.alancamargo.hearthstone.core.extensions.createIntent
import com.alancamargo.hearthstone.core.extensions.observeViewModelFlow
import com.alancamargo.hearthstone.core.extensions.putArguments
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

private const val DIALOGUE_TAG = "card_image_dialogue"

@AndroidEntryPoint
internal class CardListActivity : AppCompatActivity() {

    private var _binding: ActivityCardListBinding? = null
    private val binding: ActivityCardListBinding
        get() = _binding!!

    private val args by args<Args>()
    private val viewModel by viewModels<CardsViewModel>()

    private val adapter = CardAdapter()

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCardListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeViewModelFlow(viewModel.state, ::onStateChanged)
        observeViewModelFlow(viewModel.action, ::onAction)
        viewModel.loadCards(args.type, args.filter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cards, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemClearCache -> viewModel.onClearCardsCacheClicked()
            android.R.id.home -> viewModel.onBackClicked()
        }

        return true
    }

    private fun setUpUi() = with(binding) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = args.filter

        recyclerView.adapter = adapter

        btTryAgain.setOnClickListener {
            viewModel.loadCards(args.type, args.filter)
        }
    }

    private fun onStateChanged(state: CardsViewState) = with(state) {
        cards?.let(adapter::submitList)
        binding.shimmerContainer.isVisible = isLoading
        binding.recyclerView.isVisible = !isLoading && cards != null
        binding.groupError.isVisible = error != null

        error?.let {
            binding.imgError.setImageResource(it.iconRes)
            binding.txtError.setText(it.messageRes)
        }
    }

    private fun onAction(action: CardsViewAction) {
        when (action) {
            is CardsViewAction.Finish -> finish()
            is CardsViewAction.ShowCardsCacheClearedToast -> showCardsCacheClearedToast()
            is CardsViewAction.ShowErrorClearingCardsCacheToast -> showErrorClearingCardsCacheToast()
        }
    }

    private fun showCardsCacheClearedToast() {
        toastHelper.showToast(R.string.cards_cache_cleared)
    }

    private fun showErrorClearingCardsCacheToast() {
        toastHelper.showToast(R.string.error_clearing_cards_cache)
    }

    @Parcelize
    data class Args(val type: FilterType, val filter: String): Parcelable

    companion object {
        fun getIntent(context: Context, args: Args): Intent {
            return context.createIntent<CardListActivity>().putArguments(args)
        }
    }
}
