package com.alancamargo.hearthstone.cards.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class UiCard(
    val id: String,
    val name: String,
    val type: String,
    val quality: String,
    val faction: String?,
    val cost: Int,
    val attack: Int,
    val health: Int,
    val text: String,
    val race: String,
    val playerClass: String,
    val imageUrl: String?
) : Parcelable
