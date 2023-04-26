package com.alancamargo.hearthstone.filters.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alancamargo.hearthstone.filters.data.model.DbFilters

@Database(
    entities = [DbFilters::class],
    version = 1,
    exportSchema = false
)
internal abstract class FiltersDatabase : RoomDatabase() {

    abstract fun getFiltersDao(): FiltersDao
}
