package com.alancamargo.hearthstone.cards.ui.navigation

import android.content.Context
import com.alancamargo.hearthstone.cards.ui.CardListActivity
import com.alancamargo.hearthstone.core.domain.FilterType
import com.alancamargo.hearthstone.navigation.CardListActivityNavigation
import javax.inject.Inject

internal class CardListActivityNavigationImpl @Inject constructor() : CardListActivityNavigation {

    override fun startActivity(context: Context, filter: String, type: FilterType) {
        val args = CardListActivity.Args(type, filter)
        val intent = CardListActivity.getIntent(context, args)
        context.startActivity(intent)
    }
}
