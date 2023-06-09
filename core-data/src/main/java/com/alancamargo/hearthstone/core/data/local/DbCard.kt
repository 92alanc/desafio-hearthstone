package com.alancamargo.hearthstone.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CARDS")
data class DbCard(
    @PrimaryKey val name: String,
    val type: String,
    val quality: String,
    val faction: String,
    val cost: Int,
    val attack: Int,
    val health: Int,
    val text: String,
    val race: String,
    val playerClass: String,
    val imageUrl: String
)
