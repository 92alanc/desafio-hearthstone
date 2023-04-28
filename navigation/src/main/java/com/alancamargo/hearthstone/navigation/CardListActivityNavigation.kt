package com.alancamargo.hearthstone.navigation

import android.content.Context
import com.alancamargo.hearthstone.core.domain.FilterType

interface CardListActivityNavigation {

    fun startActivity(context: Context, filter: String, type: FilterType)
}
