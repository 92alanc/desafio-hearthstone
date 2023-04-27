package com.alancamargo.hearthstone.filters.data.local

import com.alancamargo.hearthstone.filters.data.db.FiltersDao
import com.alancamargo.hearthstone.filters.domain.model.FiltersResult
import com.alancamargo.hearthstone.filters.testtools.stubDbFilters
import com.alancamargo.hearthstone.filters.testtools.stubFilters
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FiltersLocalDataSourceImplTest {

    private val mockDao = mockk<FiltersDao>(relaxed = true)
    private val localDataSource = FiltersLocalDataSourceImpl(mockDao)

    @Test
    fun `when database returns filters getFilters should return Success`() {
        // GIVEN
        coEvery { mockDao.getFilters() } returns stubDbFilters()

        // WHEN
        val result = runBlocking { localDataSource.getFilters() }

        // THEN
        assertThat(result).isInstanceOf(FiltersResult.Success::class.java)
    }

    @Test
    fun `when database returns null getFilters should return GenericError`() {
        // GIVEN
        coEvery { mockDao.getFilters() } returns null

        // WHEN
        val result = runBlocking { localDataSource.getFilters() }

        // THEN
        assertThat(result).isInstanceOf(FiltersResult.GenericError::class.java)
    }

    @Test
    fun `when filters exist saveFilters should update existing record`() {
        // GIVEN
        coEvery { mockDao.getFiltersCount(id = 1) } returns 1

        // WHEN
        val filters = stubFilters()
        runBlocking { localDataSource.saveFilters(filters) }

        // THEN
        coVerify { mockDao.updateFilters(filters = any()) }
    }

    @Test
    fun `when filters do not exist saveFilters should insert a new record`() {
        // GIVEN
        coEvery { mockDao.getFiltersCount(id = 1) } returns 0

        // WHEN
        val filters = stubFilters()
        runBlocking { localDataSource.saveFilters(filters) }

        // THEN
        coEvery { mockDao.insertFilters(filters = any()) }
    }

    @Test
    fun `deleteFilters should delete filters from database`() {
        // WHEN
        runBlocking { localDataSource.deleteFilters() }

        // THEN
        coVerify { mockDao.deleteFilters() }
    }
}
