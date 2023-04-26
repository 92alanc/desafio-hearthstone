package com.alancamargo.hearthstone.filters.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alancamargo.hearthstone.filters.data.model.DbFilters

@Dao
internal interface FiltersDao {

    @Query("SELECT * FROM FILTERS")
    suspend fun getFilters(): DbFilters?

    @Insert(entity = DbFilters::class)
    suspend fun insertFilters(filters: DbFilters)

    @Update(entity = DbFilters::class)
    suspend fun updateFilters(filters: DbFilters)

    @Query("DELETE FROM FILTERS")
    suspend fun deleteFilters()

    @Query("SELECT COUNT() FROM FILTERS WHERE id = :id")
    suspend fun getFiltersCount(id: Long): Int
}
