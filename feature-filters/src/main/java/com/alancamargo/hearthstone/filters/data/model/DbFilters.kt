package com.alancamargo.hearthstone.filters.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FILTERS")
internal data class DbFilters(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val factionsJson: String,
    val qualitiesJson: String,
    val racesJson: String,
    val typesJson: String,
    val playerClassesJson: String
)
