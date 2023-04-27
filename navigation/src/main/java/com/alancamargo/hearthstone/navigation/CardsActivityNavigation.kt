package com.alancamargo.hearthstone.navigation

import android.content.Context
import com.alancamargo.hearthstone.core.domain.FilterType

interface CardsActivityNavigation {

    fun startActivity(context: Context, filterType: FilterType)
}
