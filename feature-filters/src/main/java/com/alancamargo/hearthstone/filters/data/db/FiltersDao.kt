package com.alancamargo.hearthstone.filters.data.db

import androidx.room.Dao
import androidx.room.Query
import com.alancamargo.hearthstone.filters.data.model.DbFilters

@Dao
internal interface FiltersDao {

    @Query("SELECT * FROM FILTERS")
    suspend fun getFilters(): DbFilters?
}
